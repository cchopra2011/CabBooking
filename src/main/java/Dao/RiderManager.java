package Dao;

import java.util.HashMap;
import java.util.Map;

import Model.*;
import lombok.NonNull;
import Exception.*;

public class RiderManager {
    

    Map<String, Rider> riders = new HashMap<>();

    public void createRider(final Rider newRider) {
        if (riders.containsKey(newRider.getId())) {
          throw new RiderAlreadyExistsException();
        }
    
        riders.put(newRider.getId(), newRider);
      }

      public Rider getRider(@NonNull final String riderId) {
        if (!riders.containsKey(riderId)) {
          throw new RiderNotFoundException();
        }
        return riders.get(riderId);
      }

      public void updateContactDetails(String riderId, String contactNum) {
        if(!riders.containsKey(riderId)){
          throw new RiderNotFoundException();
        }
        riders.get(riderId).setContactNumber(contactNum);
      }

      public void updateRiderLocation(String riderId, Location riderLocation) {
        if(!riders.containsKey(riderId)){
              throw new RiderNotFoundException();
        }
        riders.get(riderId).setRiderLocation(riderLocation);
    }
}
