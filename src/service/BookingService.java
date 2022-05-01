package service;

import dto.*;

import java.util.*;

public class BookingService {

    public static Map<String, Booking> bookingMap = new HashMap<>();


    public static long bookVehicle(String branchName, String vehicleType, Long startTime, Long endTime){
        Branch branch = BranchService.getBranchByName( branchName );
        if(branch == null){
            return -1;
        }

        List<Vehicle> vehicles = branch.getVehicles();
        Map<String,Vehicle> bookedVehicleIdInTimeRange = getBookedVehicleIdInTimeRange( startTime, endTime, vehicleType, branch );
        Collections.sort( vehicles, new Comparator<Vehicle>() {
            @Override
            public int compare( Vehicle o1, Vehicle o2 ) {
                if(o1.getPrice() < o2.getPrice()){
                    return -1;
                }return 0;
            }
        } );
        for(Vehicle vehicle : vehicles){
            if(vehicle.getVehicleType().name().equals( vehicleType ) && !bookedVehicleIdInTimeRange.containsKey( vehicle.getId() )){
                Booking booking = createBooking(branch, vehicleType, startTime, endTime, vehicle);
                return Math.round( (endTime - startTime) *booking.getPrice());
            }
        }
        return -1l;
    }


    private static Booking createBooking( Branch branch, String vehicleType, Long startTime, Long endTime , Vehicle vehicle) {
        Booking booking = new Booking();
        booking.setBranch( branch );
        booking.setId( UUID.randomUUID().toString() );
        booking.setVehicle( vehicle );
        Pair pair = new Pair();
        pair.setEnd( endTime );
        pair.setStart( startTime );
        booking.setTimeSlot( pair );
        double price = PricingFactory.getPricing( branch, startTime, endTime, vehicle.getPrice() );
        booking.setPrice( price );
        bookingMap.put( booking.getId(), booking );
        return booking;
    }

    public static Map<String,Vehicle> getBookedVehicleIdInTimeRange( Long start , Long end, String vehicleType, Branch branch){
        Map<String, Vehicle> vehicleIdVehicleMap = new HashMap<>();
        for(String bookingId : bookingMap.keySet()){
            Booking booking = bookingMap.get( bookingId );
            if(!booking.getVehicle().getVehicleType().name().equals( vehicleType ) ||
                     !booking.getBranch().getId().equals( branch.getId() ) ){
                continue;
            }else if( isBooked( booking, start, end ) ) {
                vehicleIdVehicleMap.put( booking.getVehicle().getId(), booking.getVehicle() );
            }
        }
        return vehicleIdVehicleMap;
    }

    public static boolean isBooked(Booking booking, long start , long end){
        if(start > booking.getTimeSlot().getEnd()  || booking.getTimeSlot().getStart() > end  ){
            return false;
        }
            return true;
    }

    public static int totalBookVehicleOfTypeInTimeRangeForBranch(String vehicleType, Long startTime , Long endTime, Branch branch){

        int totalBookedVehicle = 0;
        for(String bookingId : bookingMap.keySet()){
            Booking booking = bookingMap.get( bookingId );
            if(booking.getBranch().getName().equals( branch.getName() ) && booking.getTimeSlot().getEnd() >= startTime
                    && booking.getVehicle().getVehicleType().name().equals( VehicleType.CAR.name() )){
                totalBookedVehicle++;
            }
        }
        return totalBookedVehicle;
    }
}
