package Model;

import lombok.ToString;

enum TripStatus{
    IN_PROGRESS,
    FINISHED
}

public class Trip {
    
    private Rider rider;
    private Cab cab;
    private Double price;
    private Location fromPoint;
    private Location toPoint;
    private TripStatus tripStatus;

    public Trip(Rider rider, Cab cab, Double price, Location fromPoint, Location toPoint) {
        this.rider = rider;
        this.cab = cab;
        this.price = price;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.tripStatus= TripStatus.IN_PROGRESS;
    }

    public void endTrip(){
        this.tripStatus = TripStatus.FINISHED;
    }
    

    

    public Double getPrice() {
        return price;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    @Override
    public String toString() {
        return "Trip [cab=" + cab + ", fromPoint=" + fromPoint + ", price=" + price + ", rider=" + rider + ", toPoint="
                + toPoint + ", tripStatus=" + tripStatus + "]";
    }

    
    

}
