package com.example.vehiclerentalservice.service.strategy;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.vehiclerentalservice.model.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class LowestPriceSelectionStrategy implements VehicleSelectionStrategy {
    @Override
    public Optional<Vehicle> selectVehicle(List<Vehicle> vehicles, Date startTime, Date endTime) {
        if (vehicles == null) {
            return Optional.empty();
        }

        return vehicles.stream()
                .filter(vehicle -> vehicle.isAvailable(startTime, endTime))
                .min((v1, v2) -> Double.compare(v1.getHourlyRate(), v2.getHourlyRate()));
    }
}
