package dto;

import java.util.List;
import java.util.Set;

public class Branch {

    private String id;

    private String name;


    private List<Vehicle> vehicles;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles( List<Vehicle> vehicles ) {
        this.vehicles = vehicles;
    }


    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }


    public Set<VehicleType> getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType( Set<VehicleType> vehicleType ) {
        this.vehicleType = vehicleType;
    }

    private Set<VehicleType> vehicleType;


    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

}
