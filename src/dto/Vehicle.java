package dto;

public class Vehicle {

    private String id;

    private VehicleType vehicleType;

    private Double price;

    private Branch branch;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType( VehicleType vehicleType ) {
        this.vehicleType = vehicleType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice( Double price ) {
        this.price = price;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch( Branch branch ) {
        this.branch = branch;
    }
}
