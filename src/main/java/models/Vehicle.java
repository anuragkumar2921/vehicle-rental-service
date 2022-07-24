package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Vehicle {
    private String vehicleId;
    private String branchName;
    private String vehicleType;
    private boolean isBooked;
    private int bookPrice;
    private int bookedFrom;
    private int bookedUpto;
}
