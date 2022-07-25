import models.Branch;
import models.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    Map<String, Branch> branchMapping = new HashMap<>();

    @Override
    public boolean addBranch(String branchName, List<String> vehicleTypes) {
//        Branch branch = Branch.builder()
//                .name(branchName)
//                .vehicleList(new ArrayList<>())
//                .vehicleTypes(vehicleTypes).build();
        Branch branch = new Branch(branchName,vehicleTypes, new ArrayList<>());
        branchMapping.put(branchName, branch);
        return true;
    }

    @Override
    public boolean addVehicle(String branchName, String vehicleType, String vehicleId, int price) {
        Branch branch = branchMapping.get(branchName);
        if (Objects.isNull(branch) || !branch.getVehicleTypes().contains(vehicleType)) {
            return false;
        }
//        Vehicle vehicle = Vehicle.builder()
//                .vehicleId(vehicleId)
//                .branchName(branchName)
//                .vehicleType(vehicleType)
//                .bookPrice(price).build();
        Vehicle vehicle = new Vehicle(vehicleId, branchName, vehicleType, false, price);
        branch.getVehicleList().add(vehicle);
        return true;
    }

    @Override
    public int book(String branchName, String vehicleType, int startTime, int endTime) {
        Branch branch = branchMapping.get(branchName);
        if (branch == null || !branch.getVehicleTypes().contains(vehicleType)) {
            return -1;
        }
        List<Vehicle> availableVehicles = branch.getVehicleList()
                .stream()
                .filter(v -> v.getVehicleType().equals(vehicleType) &&
                        (!v.isBooked() ||
                        (v.getBookedFrom() > startTime && v.getBookedFrom() > endTime) ||
                        (v.getBookedUpto() < startTime && v.getBookedUpto() < endTime)))
                .sorted(Comparator.comparing(Vehicle::getBookPrice))
                .collect(Collectors.toList());
        if (availableVehicles.isEmpty()) return -1;
        Vehicle vehicle = availableVehicles.get(0);
        vehicle.setBooked(true);
        vehicle.setBookedFrom(startTime);
        vehicle.setBookedUpto(endTime);
        int slotCount = endTime - startTime;
        return dynamicPricingApplicable(branch, vehicleType)
                ? (int) ((vehicle.getBookPrice() * 0.1 + vehicle.getBookPrice()) * slotCount) : vehicle.getBookPrice() * slotCount;
    }

    @Override
    public List<String> displayVehicle(String branchName, int startTime, int endTime) {
        Branch branch = branchMapping.get(branchName);
        if (branch == null) {
            return new ArrayList<>();
        }
        return branch.getVehicleList()
                .stream()
                .filter(v -> !v.isBooked() ||
                        ((v.getBookedFrom() > startTime && v.getBookedFrom() > endTime) ||
                        (v.getBookedUpto() < startTime && v.getBookedUpto() < endTime)))
                .sorted(Comparator.comparing(Vehicle::getBookPrice))
                .map(Vehicle::getVehicleId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean dropVehicle(String branchName, String vehicleId) {
        Branch branch = branchMapping.get(branchName);
        Optional<Vehicle> vehicleOptional = branch.getVehicleList()
                .stream()
                .filter(v -> v.getVehicleId().equals(vehicleId))
                .findFirst();
        if (!vehicleOptional.isPresent()) return false;
        Vehicle vehicle = vehicleOptional.get();
        vehicle.setBooked(false);
        vehicle.setBookedFrom(0);
        vehicle.setBookedUpto(0);
        return true;
    }

    private boolean dynamicPricingApplicable(Branch branch, String vehicleType) {
        int bookedVehicle = 0;
        int totalVehicle = 0;
        for (Vehicle vehicle : branch.getVehicleList()) {
            if (vehicle.getVehicleType().equals(vehicleType)) {
                totalVehicle++;
                if (vehicle.isBooked()) bookedVehicle++;
            }
        }
        double bookingPercent = (double) (bookedVehicle / totalVehicle) * 100;
        return bookingPercent >= 80;
    }
}
