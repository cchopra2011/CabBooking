package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Exception.CabAlreadyExistException;
import Exception.CabNotFoundException;
import Model.Cab;
import Model.Location;

public class CabManager {
    
    HashMap<String,Cab> allCabs = new HashMap<>();

    public void createCab(final Cab newCab){
           if(allCabs.containsKey(newCab.getId())){
               throw new CabAlreadyExistException();
           }
           allCabs.put(newCab.getId(), newCab);  
    }

    public void updateCabLocation(String cabId, Location cabLocation) {
        if(!allCabs.containsKey(cabId)){
              throw new CabNotFoundException();
        }
        allCabs.get(cabId).setCurrentLocation(cabLocation);
    }

    public void updateCabAvailability(String cabId, Boolean newAvailability) {
        if(!allCabs.containsKey(cabId)){
            throw new CabNotFoundException();
      }
      allCabs.get(cabId).setAvailable(newAvailability);;
    }

    public List<Cab> getCabs(Location locationFrom, Double maxAllowedTripMatchingDistance) {
        List<Cab> result = new ArrayList<>();

        for(Cab cab : allCabs.values()){

            if(cab.isAvailable() && cab.getCurrentTrip()==null && cab.getCurrentLocation().getDistance(locationFrom)<=maxAllowedTripMatchingDistance){

                result.add(cab);
            }
        }
        return result;
    }

    public Cab getCab(String cabId) {
        if (!allCabs.containsKey(cabId)) {
            throw new CabNotFoundException();
          }
          return allCabs.get(cabId);
    }

    
}
