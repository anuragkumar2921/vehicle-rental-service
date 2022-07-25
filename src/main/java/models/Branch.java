package models;

import java.util.List;

public class Branch {
    private String name;
    private List<String> vehicleTypes;
    private List<Vehicle> vehicleList;

    public Branch(String name, List<String> vehicleTypes, List<Vehicle> vehicleList) {
        this.name = name;
        this.vehicleTypes = vehicleTypes;
        this.vehicleList = vehicleList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<String> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
