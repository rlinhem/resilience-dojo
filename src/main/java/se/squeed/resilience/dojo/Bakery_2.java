package se.squeed.resilience.dojo;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import se.squeed.resilience.dojo.products.Cake;
import se.squeed.resilience.dojo.products.Ingredients;
import se.squeed.resilience.dojo.stations.MixStation;
import se.squeed.resilience.dojo.stations.OvenStation;

import javax.inject.Inject;

class Bakery_2 {

    @Inject
    private MixStation mixStation;

    @Inject
    private OvenStation ovenStation;

    private final CircuitBreaker circuitBreaker;

    /**
     * Welcome to your second bakery!
     * <p>
     * You have now hired a baker! Yay. However, you start to notice that your baker is not very
     * good. Your baker burns half of all the cakes
     * <p>
     * You don't want to waste precious ingredients so you need to protect yourself.
     * <p>
     * Mission: Construct a Circuit Breaker that should trip/open if your baker has failed to bake 20 cakes.
     */

    private Bakery_2() {
        circuitBreaker = null;
    }

    Cake bakeCake(Ingredients ingredients) {
        return null;
    }
}
