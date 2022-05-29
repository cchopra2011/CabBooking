package Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Dao.*;
import Model.Cab;
import Model.Location;;

@RestController
public class CabController {
    
   private CabManager cabManager;
   private TripManager tripManager;


public CabController(Dao.CabManager cabManager, TripManager tripManager) {
    this.cabManager = cabManager;
    this.tripManager = tripManager;
}


@PostMapping(value = "/register/cab")
public ResponseEntity registerCab(@RequestParam final String cabId,@RequestParam final String driverName){

    cabManager.createCab(new Cab(cabId, driverName));

return ResponseEntity.ok("created with "+cabId+" -  Driver Name  :"+driverName);
}

@PostMapping(value = "/update/cab/location")
public ResponseEntity updateCabLocation(@RequestParam final String cabId,@RequestParam final Double newLocationX
                                  ,@RequestParam final Double newLocationY){

    cabManager.updateCabLocation(cabId, new Location(newLocationX,newLocationY));

return ResponseEntity.ok("");
}

@PostMapping(value = "/update/cab/availability")
public ResponseEntity updateCabAvailability(@RequestParam final String cabId,@RequestParam final Boolean newAvailability){

    cabManager.updateCabAvailability(cabId, newAvailability);

return ResponseEntity.ok("");
}

@PostMapping(value = "/update/cab/endTrip")
public ResponseEntity endTrip(@RequestParam final String cabId){

    tripManager.endTrip(cabManager.getCab(cabId));

return ResponseEntity.ok("");
}
   

}
