package service;

import dto.Booking;
import dto.Branch;
import dto.Vehicle;
import dto.VehicleType;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleService {

    private static Map<String,Vehicle> vehicleMap = new HashMap<>();

    public static Set<String> availableVehicleType = Arrays.stream( VehicleType.values() ).map( d->d.name() ).collect( Collectors.toSet()) ;


    public static boolean addVehicle( String branchName, String vehicleType, String vehicleId, Double price ) {
        Branch branch = BranchService.getBranchByName( branchName );
        if(vehicleMap.containsKey( vehicleId ) || !availableVehicleType.contains( vehicleType ) || branch == null){
            return false;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId( vehicleId );
        vehicle.setPrice( price );
        vehicle.setVehicleType( VehicleType.valueOf( vehicleType ) );
        branch.getVehicleType().add( VehicleType.valueOf( vehicleType ) );
        branch.getVehicles().add( vehicle );
        vehicle.setBranch( BranchService.getBranchByName( branchName ) );
        vehicleMap.put( vehicleId,vehicle );
        return true;
    }

    public static String getVehicle( String branchName, Long start, Long end ) {
        Map<String,Booking> bookingMap = BookingService.bookingMap;
        Set<String> bookedVehiclesInRange = new HashSet<>();
        Branch branch = null;
        for(String bookingId : bookingMap.keySet()){
            Booking booking = bookingMap.get( bookingId );
            branch = booking.getBranch();
            if(booking.getBranch().getName().equals( branchName ) && BookingService.isBooked( booking, start, end )){
                bookedVehiclesInRange.add( booking.getVehicle().getId() );
            }
        }
        if(branch == null || branch.getVehicles() == null || branch.getVehicles().size() == 0){
            return "";
        }
        List<Vehicle> availablableVehicles = new ArrayList<>();
        for(Vehicle vehicle : branch.getVehicles()){
            if(!bookedVehiclesInRange.contains( vehicle.getId() )){
                availablableVehicles.add( vehicle );
            }
        }
        if(availablableVehicles.size() == 0){
            return "";
        }

        Collections.sort( availablableVehicles, new Comparator<Vehicle>() {
            @Override
            public int compare( Vehicle o1, Vehicle o2 ) {
                if(o1.getPrice() < o2.getPrice()){
                    return -1;
                }else {
                    return 0;
                }
            }
        } );

    String vehicleIdCommaSeparated = "";
    for(Vehicle vehicle : availablableVehicles){
        vehicleIdCommaSeparated = vehicleIdCommaSeparated.concat( vehicle.getId()+"," );
    }
    if(vehicleIdCommaSeparated.length() != 0){
        return vehicleIdCommaSeparated.substring( 0, vehicleIdCommaSeparated.length()-1 );
    }
    return "";
}
}
