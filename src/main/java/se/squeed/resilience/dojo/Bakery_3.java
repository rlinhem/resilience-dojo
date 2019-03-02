package se.squeed.resilience.dojo;

import io.github.resilience4j.timelimiter.TimeLimiter;
import se.squeed.resilience.dojo.products.Cake;
import se.squeed.resilience.dojo.products.Ingredients;
import se.squeed.resilience.dojo.stations.MixStation;
import se.squeed.resilience.dojo.stations.OvenStation;

import javax.inject.Inject;

class Bakery_3 {

    @Inject
    private MixStation mixStation;

    @Inject
    private OvenStation ovenStation;

    private final TimeLimiter timeLimiter;

    /**
     * Welcome to your third bakery!
     *
     * <p>You have now hired a mixer! Yay. However, you start to notice that your mixer is very
     * sleepy. Your mixer falls asleep every other time he mixes something
     *
     * <p>If you don't make sure that he can be awoken, you will be stuck in eternity waiting for his
     * mix.
     *
     * <p>Construct a Time Limiter that should interrupt your mixer if he has failed to mix the
     * ingredients in a reasonable time (100-500 milliseconds).
     */

    private Bakery_3() {
        timeLimiter = null;
    }

    Cake bakeCake(Ingredients ingredients) {
        return null;
    }
}
