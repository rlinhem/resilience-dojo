package se.squeed.resilience.dojo;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.timelimiter.TimeLimiter;
import se.squeed.resilience.dojo.products.Cake;
import se.squeed.resilience.dojo.products.Ingredients;
import se.squeed.resilience.dojo.stations.MixStation;
import se.squeed.resilience.dojo.stations.OvenStation;

import javax.inject.Inject;

class Bakery_5 {

    @Inject
    private MixStation mixStation;

    @Inject
    private OvenStation ovenStation;

    private final TimeLimiter timeLimiter;

    private final CircuitBreaker circuitBreaker;


    /**
     * Welcome to your final bakery!
     *
     * <p>I can't believe you havn't fired your staff yet! They are still burning and sleeping on the job. You must really love them.
     *
     * <p>You need to make sure that your bakery can still function even though some sleeping AND burning is taking place.
     *
     * <p>Construct a Time Limiter AND a Circuit Breaker working in conjunction.
     * You may reuse your previous Time Limiter that should interrupt your mixer if he has failed to mix the ingredients in a reasonable time (100-500 milliseconds).
     * You may reuse your previous Circuit Breaker but this time it should trip/open if your mixer and baker has failed to mix/bake 20 cakes in total.
     */

    private Bakery_5() {
        timeLimiter = null;
        circuitBreaker = null;
    }

    Cake bakeCake(Ingredients ingredients) {
        return null;
    }
}
