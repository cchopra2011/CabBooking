package Model;

import lombok.Getter;
import lombok.ToString;


public class Location {

    private Double x;
    private Double y;

    public Location(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getDistance(Location location){
        return Math.sqrt(Math.pow(this.x-location.x, 2) + Math.pow(this.y-location.y, 2));
    }

    public Double getX() {
        return x;
    }
    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + "]";
    }

    
    
    
}
