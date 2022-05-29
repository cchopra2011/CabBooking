package Strategies;

import Model.Location;

public class DefaultPriceMatchingStrategy implements PriceMatchingStrategy {

    public static final Double PER_KM_RATE = 10.0;

@Override
public Double findPrice(Location fromPoint, Location toPoint) {
    return fromPoint.getDistance(toPoint) * PER_KM_RATE;
}

}
