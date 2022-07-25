package models;

public class Vehicle {
    private String vehicleId;
    private String branchName;
    private String vehicleType;
    private boolean isBooked;
    private int bookPrice;
    private int bookedFrom;
    private int bookedUpto;

    public Vehicle(String vehicleId, String branchName, String vehicleType, boolean isBooked, int bookPrice) {
        this.vehicleId = vehicleId;
        this.branchName = branchName;
        this.vehicleType = vehicleType;
        this.isBooked = isBooked;
        this.bookPrice = bookPrice;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(int bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public int getBookedUpto() {
        return bookedUpto;
    }

    public void setBookedUpto(int bookedUpto) {
        this.bookedUpto = bookedUpto;
    }
}
