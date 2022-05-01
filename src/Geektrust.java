import service.ActionController;
import service.BranchService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Geektrust {

    public static void main( String[] args ) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("file.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            process( line );
            while (line != null) {
                line = br.readLine();
                process(line);
            }
        } finally {
            br.close();
        }
    }

    private static void process( String line ) {
        if(line == null ){
            return;
        }
        String[] inputs = line.split( " " );
        String command = inputs[0];
        if(command.equalsIgnoreCase( "ADD_BRANCH" )){
            String branchName = inputs[1];
            String[] vehicleTypeCommaSEparated = inputs[2].split( "," );
            Set<String> vehicleType = new HashSet<>();
            for(int i = 0; i< vehicleTypeCommaSEparated.length ; i++){
                vehicleType.add( vehicleTypeCommaSEparated[i] );
            }
            System.out.println(ActionController.addBranch( branchName, vehicleType ));

        }else if(command.equalsIgnoreCase(  "ADD_VEHICLE" )){

            System.out.println(ActionController.addVehicle( inputs[1], inputs[2], inputs[3], Double.parseDouble( inputs[4] )));

        }else if(command.equalsIgnoreCase( "BOOK" )){

            System.out.println(ActionController.bookVehicle( inputs[1], inputs[2], Long.parseLong( inputs[3] ), Long.parseLong( inputs[4] ) ));

        }else if(command.equalsIgnoreCase( "DISPLAY_VEHICLES" )){

            System.out.println(ActionController.getVehicleForBranch( inputs[1], Long.parseLong( inputs[2] ), Long.parseLong( inputs[3] ) ));
        }
    }

}

