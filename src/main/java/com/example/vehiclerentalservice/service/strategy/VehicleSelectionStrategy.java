package com.example.vehiclerentalservice.service.strategy;


import com.example.vehiclerentalservice.model.Vehicle;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface VehicleSelectionStrategy {
    Optional<Vehicle> selectVehicle(List<Vehicle> vehicles, Date startTime, Date endTime);
}

