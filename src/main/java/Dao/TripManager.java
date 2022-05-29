package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import Exception.NoCabsAvailableException;
import Exception.TripNotFoundException;
import Model.Cab;
import Model.Location;
import Model.Rider;
import Model.Trip;
import Strategies.PriceMatchingStrategy;

public class TripManager {
    
    private Map<String,List<Trip>> trips =new HashMap<>();
    private static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 5.0;

    private CabManager cabManager;
    private RiderManager riderManager;
    private PriceMatchingStrategy priceMatchingStrategy;
    
    public TripManager(CabManager cabManager, RiderManager riderManager,
            PriceMatchingStrategy priceMatchingStrategy) {
        this.cabManager = cabManager;
        this.riderManager = riderManager;
        this.priceMatchingStrategy = priceMatchingStrategy;
    }


    public void createTrip(Rider rider, String cabId,Location locationFrom, Location locationTo) {

        Double price = priceMatchingStrategy.findPrice(locationFrom, locationTo);
        Trip newTrip = new Trip(rider, cabManager.getCab(cabId),price,locationFrom,locationTo);
        if(!trips.containsKey(rider.getId())){

            trips.put(rider.getId(), new ArrayList<>());
        }

        trips.get(rider.getId()).add(newTrip);
        cabManager.getCab(cabId).setCurrentTrip(newTrip);
    }



    public List<Trip> getTripHistory(final Rider rider) {
        return trips.get(rider.getId());
    }


    public void endTrip(Cab cab) {
        if(cab.getCurrentTrip() == null){
            throw new TripNotFoundException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }


    public List<Cab> getAvailableCabs(Rider rider, Location riderLocation, Location desLocation) {
        List<Cab> closeByAvailableCabs = cabManager.getCabs(riderLocation,MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
       
        if(closeByAvailableCabs ==  null){
            throw new NoCabsAvailableException();
        }

        return closeByAvailableCabs;
    }


    public Double getTotalAmount(Rider rider) {
        List<Trip> aTrips = trips.get(rider.getId());
        Trip trip = aTrips.stream().skip(aTrips.size() - 1).findFirst().orElse(null);
        return trip.getPrice();
    }

}
