package dto;

public class Booking {

    private String id;

    private Pair timeSlot;

    private Vehicle vehicle;

    private double price;

    private Branch branch;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public Pair getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot( Pair timeSlot ) {
        this.timeSlot = timeSlot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle( Vehicle vehicle ) {
        this.vehicle = vehicle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double price ) {
        this.price = price;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch( Branch branch ) {
        this.branch = branch;
    }
}
