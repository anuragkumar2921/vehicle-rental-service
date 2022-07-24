import java.util.List;

public interface BookingService {
    boolean addBranch(String branchName, List<String> vehicleTypes);
    boolean addVehicle(String branchName, String vehicleType, String vehicleId, int price);
    int book(String branchName, String vehicleType, int startTime, int endTime);
    List<String> displayVehicle(String branchName, int startTime, int endTime);
    boolean dropVehicle(String branchName, String vehicleId);
}
