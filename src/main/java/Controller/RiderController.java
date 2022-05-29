package Controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import javax.xml.ws.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Dao.RiderManager;
import Dao.TripManager;
import Model.Cab;
import Model.Location;
import Model.Rider;
import Model.Trip;

public class RiderController {
    private RiderManager riderManager;
    private TripManager tripManager;
    
    public RiderController(RiderManager riderManager, TripManager tripManager) {
        this.riderManager = riderManager;
        this.tripManager = tripManager;
    }


    @PostMapping(value = "/register/rider")
    public ResponseEntity registerRider(@RequestParam final String riderId, @RequestParam final String riderName,@RequestParam final String contactNum){

        riderManager.createRider(new Rider(riderId, riderName,contactNum));

        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/update/rider")
    public ResponseEntity updateContactDetails(@RequestParam final String riderId,@RequestParam final String contactNum){

        riderManager.updateContactDetails(riderId,contactNum);

        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/update/rider/location")
public ResponseEntity updateRiderLocation(@RequestParam final String riderId,@RequestParam final Double newLocationX
                                  , @RequestParam final Double newLocationY){

    riderManager.updateRiderLocation(riderId, new Location(newLocationX,newLocationY));

return ResponseEntity.ok("");
}

    
    @GetMapping(value = "/find")
    public ResponseEntity find(
        @RequestParam  final String riderId,
        @RequestParam  final Double destX,
        @RequestParam  final Double destY){

        List<Cab> listAvailableCabs = tripManager.getAvailableCabs(riderManager.getRider(riderId),riderManager.getRider(riderId).getRiderLocation(),new Location(destX,destY));
        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/book")
    public ResponseEntity book(
        @RequestParam  final String riderId,
        @RequestParam  final String cabId,
        @RequestParam  final Double destX,
        @RequestParam  final Double destY){


        tripManager.createTrip(riderManager.getRider(riderId),cabId,riderManager.getRider(riderId).getRiderLocation(),new Location(destX,destY));
        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/book")
    public ResponseEntity<List<Trip>> fetchHistory(@RequestParam final String riderId){

        List<Trip> trips = tripManager.getTripHistory(riderManager.getRider(riderId));
        return ResponseEntity.accepted().body(trips);
    }

    @GetMapping(value = "/calculatebill")
    public ResponseEntity calculateBill(@RequestParam final String riderId){
        Double  tripAmount = tripManager.getTotalAmount(riderManager.getRider(riderId));
        return ResponseEntity.ok(tripAmount);
    }
    
    

}
