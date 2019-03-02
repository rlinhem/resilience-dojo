package se.squeed.resilience.dojo.stations;

import se.squeed.resilience.dojo.products.CakeMix;
import se.squeed.resilience.dojo.products.Ingredients;

public interface MixStation {

    CakeMix mix(Ingredients ingredients);
}
