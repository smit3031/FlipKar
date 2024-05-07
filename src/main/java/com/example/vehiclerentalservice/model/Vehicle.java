package com.example.vehiclerentalservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vehicle {
    private final VehicleType type;
    private final double hourlyRate;
    private final List<Booking> bookings;

    public Vehicle(VehicleType type, double hourlyRate) {
        this.type = type;
        this.hourlyRate = hourlyRate;
        this.bookings = new ArrayList<>();
    }

    public VehicleType getType() {
        return type;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public boolean isAvailable(Date startTime, Date endTime) {
        return bookings.stream().noneMatch(booking -> booking.overlaps(startTime, endTime));
    }
}

