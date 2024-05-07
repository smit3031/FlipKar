package com.example.vehiclerentalservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.vehiclerentalservice.model.Booking;
import com.example.vehiclerentalservice.model.Branch;
import com.example.vehiclerentalservice.model.Vehicle;
import com.example.vehiclerentalservice.model.VehicleType;
import com.example.vehiclerentalservice.repository.BranchRepository;
import com.example.vehiclerentalservice.service.strategy.VehicleSelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RentalService {
    private final BranchRepository branchRepository;
    private final VehicleSelectionStrategy vehicleSelectionStrategy;

    @Autowired
    public RentalService(BranchRepository branchRepository, VehicleSelectionStrategy vehicleSelectionStrategy) {
        this.branchRepository = branchRepository;
        this.vehicleSelectionStrategy = vehicleSelectionStrategy;
    }

    public void addBranch(String name, Branch branch) {
        branchRepository.addBranch(name, branch);
    }

    public void addVehicle(String branchName, VehicleType type, int count, double hourlyRate) {
        Branch branch = branchRepository.getBranch(branchName);
        if (branch != null) {
            List<Vehicle> newVehicles = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                newVehicles.add(new Vehicle(type, hourlyRate));
            }
            branch.addVehicles(type, newVehicles);
        } else {
            System.out.println("Branch not found: " + branchName);
        }
    }



    public void rentVehicle(VehicleType type, Date startTime, Date endTime) {
        Branch bestBranch = null;
        Optional<Vehicle> bestVehicle = Optional.empty();

        for (Branch branch : branchRepository.getAllBranches()) {
            Optional<Vehicle> vehicle = vehicleSelectionStrategy.selectVehicle(branch.getVehicles().get(type), startTime, endTime);
            if (vehicle.isPresent() && (!bestVehicle.isPresent() || vehicle.get().getHourlyRate() < bestVehicle.get().getHourlyRate())) {
                bestBranch = branch;
                bestVehicle = vehicle;
            }
        }

        if (bestBranch != null && bestVehicle.isPresent()) {
            Vehicle vehicle = bestVehicle.get();
            vehicle.addBooking(new Booking(startTime, endTime));
            System.out.println("Vehicle booked from branch '" + bestBranch.getName() + "' with hourly rate of Rs." + vehicle.getHourlyRate());
        } else {
            System.out.println("No available vehicle of type '" + type + "' for the given time slot.");
        }
    }

    public void printSystemView(Date startTime, Date endTime) {
        for (Branch branch : branchRepository.getAllBranches()) {
            System.out.println("'" + branch.getName() + "':");
            branch.getVehicles().forEach((type, vehicleList) -> {
                int availableCount = 0;
                double rate = 0.0;

                for (Vehicle vehicle : vehicleList) {
                    if (vehicle.isAvailable(startTime, endTime)) {
                        availableCount++;
                        rate = vehicle.getHourlyRate();
                    }
                }

                if (availableCount > 0) {
                    System.out.println("\"" + type + "\" is available for Rs." + rate + " with " + availableCount + " vehicles available");
                } else {
                    System.out.println("All \"" + type + "\" are booked.");
                }
            });
        }
    }
}

