package service;

import dto.Booking;
import dto.Branch;
import dto.Vehicle;
import dto.VehicleType;

import java.util.List;
import java.util.Map;

public interface PricingFactory {

    public static double getPricing( Branch branch, Long startTime, Long endTime, double price ){
        int totalCarForBranch = BranchService.totalVehicleForType( VehicleType.CAR.name(), branch );
        int totalBookCarForBranch = BookingService.totalBookVehicleOfTypeInTimeRangeForBranch( VehicleType.CAR.name(), startTime, endTime, branch);
        if(totalCarForBranch * 0.8 < totalBookCarForBranch){
            return new DynamicPricing().getPrice(price);
        }else {
            return new NormalPricing().getPrice(price);
        }

    }

}
