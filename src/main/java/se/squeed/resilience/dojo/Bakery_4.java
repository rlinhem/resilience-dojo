package se.squeed.resilience.dojo;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import se.squeed.resilience.dojo.products.Cake;
import se.squeed.resilience.dojo.products.CakeMix;
import se.squeed.resilience.dojo.products.Ingredients;
import se.squeed.resilience.dojo.stations.MixStation;
import se.squeed.resilience.dojo.stations.OvenStation;

import javax.inject.Inject;
import java.time.Duration;

class Bakery_4 {

    @Inject
    private MixStation mixStation;

    @Inject
    private OvenStation ovenStation;

    private final Retry retry;

    /**
     * Welcome to your fourth bakery!
     *
     * <p>Your baker seems to be burning alot of cakes! Oh no! This time he always burns the first one he tries to make.
     *
     * <p>You need to tell him to keep his spirit up and just give it another try!
     *
     * <p>Construct a Retry that should retry the baking if it fails.
     */

    private Bakery_4() {
        retry = Retry.of("retry", RetryConfig.custom().waitDuration(Duration.ofMillis(10)).build());
    }

    Cake bakeCake(Ingredients ingredients) {
        CakeMix mix = mixStation.mix(ingredients);
        return retry.executeSupplier(() -> ovenStation.bake(mix));
    }
}
