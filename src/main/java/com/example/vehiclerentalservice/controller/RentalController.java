package com.example.vehiclerentalservice.controller;


import java.util.Date;

import com.example.vehiclerentalservice.model.Branch;
import com.example.vehiclerentalservice.model.VehicleType;
import com.example.vehiclerentalservice.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/add-branch")
    public void addBranch(@RequestBody Branch branch) {
        rentalService.addBranch(branch.getName(), branch);
    }

    @PostMapping("/rent-vehicle")
    public void rentVehicle(
            @RequestParam VehicleType type,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startTime,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endTime
    ) {
        rentalService.rentVehicle(type, startTime, endTime);
    }

    @GetMapping("/system-view")
    public void printSystemView(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startTime,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endTime
    ) {
        rentalService.printSystemView(startTime, endTime);
    }
}

