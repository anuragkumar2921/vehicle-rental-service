package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Builder
@Data
public class Branch {
    private String name;
    private List<String> vehicleTypes;
    private List<Vehicle> vehicleList;
}
