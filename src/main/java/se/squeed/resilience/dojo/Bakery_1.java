package se.squeed.resilience.dojo;

import se.squeed.resilience.dojo.products.Cake;
import se.squeed.resilience.dojo.products.CakeMix;
import se.squeed.resilience.dojo.products.Ingredients;
import se.squeed.resilience.dojo.stations.MixStation;
import se.squeed.resilience.dojo.stations.OvenStation;

import javax.inject.Inject;

class Bakery_1 {

    @Inject
    private MixStation mixStation;

    @Inject
    private OvenStation ovenStation;

    /**
     * Welcome to your first bakery!
     *
     * <p>In order to verify that you in fact can bake, please bake a simple cake
     */
    Cake bakeCake(Ingredients ingredients) {
        CakeMix mix = mixStation.mix(ingredients);
        return ovenStation.bake(mix);
    }
}
