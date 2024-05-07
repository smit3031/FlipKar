package com.example.vehiclerentalservice.model;

import java.util.*;

public class Branch {
    private final String name;
    private final Map<VehicleType, List<Vehicle>> vehicles;

    public Branch(String name) {
        this.name = name;
        this.vehicles = new HashMap<>();
    }

    public Branch(String name, Map<VehicleType, List<Vehicle>> initialVehicles) {
        this.name = name;
        this.vehicles = new HashMap<>();

        initialVehicles.forEach((type, vehicleList) -> {
            this.vehicles.put(type, new ArrayList<>(vehicleList));
        });
    }

    public String getName() {
        return name;
    }

    public Map<VehicleType, List<Vehicle>> getVehicles() {
        return vehicles;
    }

    public void addVehicles(VehicleType type, List<Vehicle> newVehicles) {
        List<Vehicle> existingVehicles = vehicles.computeIfAbsent(type, k -> new ArrayList<>());
        existingVehicles.addAll(newVehicles);
    }

    public Vehicle getAvailableVehicle(VehicleType type, Date startTime, Date endTime) {
        List<Vehicle> vehicleList = vehicles.get(type);
        if (vehicleList != null) {
            for (Vehicle vehicle : vehicleList) {
                if (vehicle.isAvailable(startTime, endTime)) {
                    return vehicle;
                }
            }
        }
        return null;
    }
}
