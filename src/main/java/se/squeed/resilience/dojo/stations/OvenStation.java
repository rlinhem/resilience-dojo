package se.squeed.resilience.dojo.stations;

import se.squeed.resilience.dojo.products.Cake;
import se.squeed.resilience.dojo.products.CakeMix;

public interface OvenStation {

    Cake bake(CakeMix mix);
}
