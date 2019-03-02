package se.squeed.resilience.dojo;

import io.github.resilience4j.circuitbreaker.CircuitBreakerOpenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.squeed.resilience.dojo.products.Cake;
import se.squeed.resilience.dojo.products.CakeMix;
import se.squeed.resilience.dojo.products.Ingredients;
import se.squeed.resilience.dojo.stations.MixStation;
import se.squeed.resilience.dojo.stations.OvenStation;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.answersWithDelay;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Bakery_5_YouNeedToFireYourStaffTest {

    @Mock
    private MixStation mixStationMock;

    @Mock
    private OvenStation ovenStationMock;

    @InjectMocks
    private Bakery_5 unitUnderTest;

    @Test
    @DisplayName("Verify that your bakery can still function even though you propably should fire your staff")
    void yourMixerIsSleeping() {
        Ingredients ingredients = new Ingredients("flour+egg+sugar");
        CakeMix cakeMix = new CakeMix("sockerkaka-mix");
        Cake cake = new Cake("sockerkaka");

        Ingredients badIngredients = new Ingredients("gravel+water+cement");
        CakeMix badCakeMix = new CakeMix("concrete-mix");

        Ingredients sleepyIngredients = new Ingredients("sleeping+powder");
        CakeMix sleepyCakeMix = new CakeMix("sleepy-mix");

        when(mixStationMock.mix(eq(ingredients))).thenReturn(cakeMix);
        when(mixStationMock.mix(eq(badIngredients))).thenReturn(badCakeMix);
        when(mixStationMock.mix(eq(sleepyIngredients))).thenAnswer(answersWithDelay(Long.MAX_VALUE, null));
        when(ovenStationMock.bake(eq(cakeMix))).thenReturn(cake);
        when(ovenStationMock.bake(eq(badCakeMix))).thenThrow(new RuntimeException("Ooops, im a bad baker"));

        List<Cake> bakedCakes = IntStream.range(0, 99).mapToObj(i -> {
            try {
                if (i % 3 == 0) {
                    Cake bakedCake = unitUnderTest.bakeCake(ingredients);
                    System.out.println("Cake was baked...");
                    return bakedCake;
                } else if (i % 3 == 1) {
                    return unitUnderTest.bakeCake(badIngredients);
                } else {
                    try {
                        return unitUnderTest.bakeCake(sleepyIngredients);
                    } catch (TimeoutException ignore) {
                        System.out.println("Failed to bake cake, perhaps someone is sleeping...?");
                        return null;
                    }
                }
            } catch (CircuitBreakerOpenException ignore) {
                return null;
            } catch (Exception ignore) {
                System.out.println("Failed to bake cake, is someone a pyromaniac...?");
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        verify(mixStationMock, times(20)).mix(eq(ingredients));
        verify(mixStationMock, times(20)).mix(eq(badIngredients));
        verify(mixStationMock, times(20)).mix(eq(sleepyIngredients));
        verify(ovenStationMock, times(20)).bake(eq(cakeMix));
        verify(ovenStationMock, times(20)).bake(eq(badCakeMix));
        verify(ovenStationMock, times(0)).bake(eq(sleepyCakeMix));

        assertThat(bakedCakes).hasSize(20);
    }
}
