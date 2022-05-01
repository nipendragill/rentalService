package service;

import dto.Branch;
import dto.Vehicle;
import dto.VehicleType;

import java.util.*;
import java.util.stream.Collectors;

public class BranchService {

    public static Map<String, Branch> branchNameBranchMap = new HashMap<>();

    private Set<String> avalableVehicleType = new HashSet<>();


    public static boolean addBranch( String branchName, Set<String> vehicleTypes ) {
        if(branchNameBranchMap.containsKey( branchName )){
            return false;
        };

        Set<VehicleType> vehicleTypesSet = new HashSet<>();
        for(String vehicleType : vehicleTypes){
            if(!VehicleService.availableVehicleType.contains( vehicleType )){
                return false;
            }
            vehicleTypesSet.add( VehicleType.valueOf( vehicleType ) );
        }
        Branch branch = new Branch();
        branch.setName( branchName );
        branch.setVehicleType(vehicleTypesSet);
        branch.setId( UUID.randomUUID().toString() );
        branch.setVehicles( new ArrayList<>() );
        branchNameBranchMap.put( branch.getId(), branch );
        return true;
    }

    public static Branch getBranchByName(String branchName){
        for(String  branchId : branchNameBranchMap.keySet()){
            if(branchNameBranchMap.get( branchId ).getName().equals( branchName )){
                return branchNameBranchMap.get( branchId );
            }
        }
        return null;

    }

    public static int totalVehicleForType(String vehicleType, Branch branch){
        List<Vehicle> vehicles = branch.getVehicles();
        int totalVehicleOFType = 0;
        for(Vehicle vehicle : vehicles){
            if(vehicle.getVehicleType().equals( VehicleType.valueOf( vehicleType ) )){
                totalVehicleOFType++;
            }
        }
        return totalVehicleOFType;
    }
}
