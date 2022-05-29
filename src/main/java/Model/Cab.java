package Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Cab {
    
    String id;
    String driverName;

    Trip currentTrip;
    Location currentLocation;
    boolean isAvailable;

    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }


    public Trip getCurrentTrip() {
        return currentTrip;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Cab [currentLocation=" + currentLocation + ", currentTrip=" + currentTrip + ", driverName=" + driverName
                + ", id=" + id + ", isAvailable=" + isAvailable + "]";
    }

    
    



    

}
