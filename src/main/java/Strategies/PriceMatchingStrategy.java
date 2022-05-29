package Strategies;

import Model.Location;

public interface PriceMatchingStrategy {
    Double findPrice(Location fromPoint, Location toPoint);
}
