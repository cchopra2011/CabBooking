package Model;

import javax.annotation.Generated;

import lombok.Getter;
import lombok.ToString;


public class Rider {
    
    String id;
    String riderName;
    String contactNumber;
    Location riderLocation;

    public Rider(String id, String riderName,String contactDetails) {
        this.id = id;
        this.riderName = riderName;
        this.contactNumber = contactDetails;
    }

    public String getId() {
        return id;
    }

    public String getRiderName() {
        return riderName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    

    public Location getRiderLocation() {
        return riderLocation;
    }

    public void setRiderLocation(Location riderLocation) {
        this.riderLocation = riderLocation;
    }

    @Override
    public String toString() {
        return "Rider [contactNumber=" + contactNumber + ", id=" + id + ", riderLocation=" + riderLocation
                + ", riderName=" + riderName + "]";
    }

    

    

}
