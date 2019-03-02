package se.squeed.resilience.dojo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import se.squeed.resilience.dojo.products.Cake;
import se.squeed.resilience.dojo.products.CakeMix;
import se.squeed.resilience.dojo.products.Ingredients;
import se.squeed.resilience.dojo.stations.MixStation;
import se.squeed.resilience.dojo.stations.OvenStation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Bakery_4_YourBakerIsHotTest {

    @Mock
    private MixStation mixStationMock;

    @Mock
    private OvenStation ovenStationMock;

    @InjectMocks
    private Bakery_4 unitUnderTest;

    @Test
    @DisplayName("Verify that your baker doesnt loose his spirits just becuase he burns cakes")
    void yourMixerIsSleeping() {
        Ingredients ingredients = new Ingredients("flour+egg+sugar");
        CakeMix cakeMix = new CakeMix("sockerkaka-mix");
        Cake cake = new Cake("sockerkaka");

        Ingredients badIngredients = new Ingredients("gasoline+flour+egg+sugar");
        CakeMix badCakeMix = new CakeMix("flammable-mix");

        when(mixStationMock.mix(eq(ingredients))).thenReturn(cakeMix);
        when(mixStationMock.mix(eq(badIngredients))).thenReturn(badCakeMix);
        when(ovenStationMock.bake(eq(cakeMix))).thenReturn(cake);
        when(ovenStationMock.bake(eq(badCakeMix))).then(new Answer<Cake>() {

            private int callNumber = 0;

            @Override
            public Cake answer(InvocationOnMock invocationOnMock) {
                if (++callNumber % 2 == 0) {
                    return cake;
                } else {
                    System.out.println("Failed to bake cake, and i smell something burning...?");
                    throw new IllegalStateException("It buuuuurns!");
                }
            }
        });

        List<Cake> bakedCakes = IntStream.range(0, 100).mapToObj(i -> {
            try {
                if (i % 2 == 0) {
                    Cake bakedCake = unitUnderTest.bakeCake(ingredients);
                    System.out.println("Cake was baked...");
                    return bakedCake;
                } else {
                    return unitUnderTest.bakeCake(badIngredients);
                }
            } catch (Exception ignore) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        verify(mixStationMock, times(50)).mix(eq(ingredients));
        verify(mixStationMock, times(50)).mix(eq(badIngredients));
        verify(ovenStationMock, times(50)).bake(eq(cakeMix));
        verify(ovenStationMock, times(100)).bake(eq(badCakeMix));

        assertThat(bakedCakes).hasSize(100);
    }
}
