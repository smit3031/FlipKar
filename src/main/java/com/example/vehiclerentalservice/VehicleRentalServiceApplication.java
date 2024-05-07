package com.example.vehiclerentalservice;

import com.example.vehiclerentalservice.model.Branch;
import com.example.vehiclerentalservice.model.Vehicle;
import com.example.vehiclerentalservice.model.VehicleType;
import com.example.vehiclerentalservice.service.RentalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class VehicleRentalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleRentalServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(RentalService rentalService) {
        return args -> {
            // Set up date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");

            // Set up branches and add initial vehicles
            // Branch 1: Koramangala
            Map<VehicleType, List<Vehicle>> koramangalaVehicles = new HashMap<>();
            koramangalaVehicles.put(VehicleType.SUV, List.of(new Vehicle(VehicleType.SUV, 12.0)));
            koramangalaVehicles.put(VehicleType.SEDAN, List.of(new Vehicle(VehicleType.SEDAN, 10.0), new Vehicle(VehicleType.SEDAN, 10.0), new Vehicle(VehicleType.SEDAN, 10.0)));
            koramangalaVehicles.put(VehicleType.BIKE, List.of(new Vehicle(VehicleType.BIKE, 20.0), new Vehicle(VehicleType.BIKE, 20.0), new Vehicle(VehicleType.BIKE, 20.0)));
            Branch koramangala = new Branch("koramangala", koramangalaVehicles);
            rentalService.addBranch("koramangala", koramangala);

            // Branch 2: Jayanagar
            Map<VehicleType, List<Vehicle>> jayanagarVehicles = new HashMap<>();
            jayanagarVehicles.put(VehicleType.SEDAN, List.of(new Vehicle(VehicleType.SEDAN, 11.0), new Vehicle(VehicleType.SEDAN, 11.0), new Vehicle(VehicleType.SEDAN, 11.0)));
            jayanagarVehicles.put(VehicleType.BIKE, List.of(new Vehicle(VehicleType.BIKE, 30.0), new Vehicle(VehicleType.BIKE, 30.0), new Vehicle(VehicleType.BIKE, 30.0)));
            jayanagarVehicles.put(VehicleType.HATCHBACK, List.of(new Vehicle(VehicleType.HATCHBACK, 8.0), new Vehicle(VehicleType.HATCHBACK, 8.0), new Vehicle(VehicleType.HATCHBACK, 8.0), new Vehicle(VehicleType.HATCHBACK, 8.0)));
            Branch jayanagar = new Branch("jayanagar", jayanagarVehicles);
            rentalService.addBranch("jayanagar", jayanagar);

            // Branch 3: Malleshwaram
            Map<VehicleType, List<Vehicle>> malleshwaramVehicles = new HashMap<>();
            malleshwaramVehicles.put(VehicleType.SUV, List.of(new Vehicle(VehicleType.SUV, 11.0)));
            malleshwaramVehicles.put(VehicleType.BIKE, List.of(new Vehicle(VehicleType.BIKE, 3.0), new Vehicle(VehicleType.BIKE, 3.0), new Vehicle(VehicleType.BIKE, 3.0), new Vehicle(VehicleType.BIKE, 3.0), new Vehicle(VehicleType.BIKE, 3.0), new Vehicle(VehicleType.BIKE, 3.0), new Vehicle(VehicleType.BIKE, 3.0), new Vehicle(VehicleType.BIKE, 3.0), new Vehicle(VehicleType.BIKE, 3.0), new Vehicle(VehicleType.BIKE, 3.0)));
            malleshwaramVehicles.put(VehicleType.SEDAN, List.of(new Vehicle(VehicleType.SEDAN, 10.0), new Vehicle(VehicleType.SEDAN, 10.0), new Vehicle(VehicleType.SEDAN, 10.0)));
            Branch malleshwaram = new Branch("malleshwaram", malleshwaramVehicles);
            rentalService.addBranch("malleshwaram", malleshwaram);

            // Add additional vehicles
            rentalService.addVehicle("koramangala", VehicleType.SEDAN, 1, 10.0); // Add 1 sedan to Koramangala

            // Define time slots for booking
            Date startTime = dateFormat.parse("20 Feb 2024 10:00 AM");
            Date endTime = dateFormat.parse("20 Feb 2024 12:00 PM");

            // Rent vehicles and print booking details
            rentalService.rentVehicle(VehicleType.SUV, startTime, endTime); // Should book from Malleshwaram
            rentalService.rentVehicle(VehicleType.SUV, startTime, endTime); // Should book from Koramangala
            rentalService.rentVehicle(VehicleType.SUV, startTime, endTime); // Should fail due to unavailability

            // Print system view for a specific time slot
            Date viewStartTime = dateFormat.parse("20 Feb 2024 11:00 AM");
            Date viewEndTime = dateFormat.parse("20 Feb 2024 12:00 PM");
            rentalService.printSystemView(viewStartTime, endTime);
        };

    }
}
