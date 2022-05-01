package service;

import dto.Vehicle;
import dto.VehicleType;

import java.util.List;
import java.util.Set;

public class ActionController {

    public static boolean addBranch( String branchName, Set<String> vehicleTypes ){
        return BranchService.addBranch(branchName, vehicleTypes);
    }

    public static boolean addVehicle(String branchName, String vehicleType, String vehicleId, Double price){
        return VehicleService.addVehicle(branchName, vehicleType, vehicleId, price);
    }

    public static long bookVehicle(String branchId, String vehicleType, Long start, Long endTime){
        return BookingService.bookVehicle( branchId, vehicleType, start, endTime );
    }

    public static String getVehicleForBranch(String branchName, Long start, Long end){
        return VehicleService.getVehicle(branchName, start, end);
    }
}
