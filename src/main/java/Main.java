import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String query = new String();
        BookingService bookingService = new BookingServiceImpl();
        while ((query = bufferedReader.readLine()) != null) {
            String[] command = query.split(" ");
            switch (command[0]) {
                case "ADD_BRANCH":
                    List<String> vehicleList = new ArrayList<>(Arrays.asList(command[2].split(",")));
                    System.out.println(bookingService.addBranch(command[1], vehicleList));
                    break;
                case "ADD_VEHICLE":
                    System.out.println(bookingService.addVehicle(command[1], command[2], command[3], Integer.valueOf(command[4])));
                    break;
                case "BOOK":
                    System.out.println(bookingService.book(command[1], command[2], Integer.valueOf(command[3]), Integer.valueOf(command[4])));
                    break;
                case "DISPLAY_VEHICLES":
                    System.out.println(bookingService.displayVehicle(command[1], Integer.valueOf(command[2]), Integer.valueOf(command[3])));
                    break;
                case "DROP_VEHICLES":
                    System.out.println(bookingService.dropVehicle(command[1], command[2]));
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }
    }
}
