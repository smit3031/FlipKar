package com.example.vehiclerentalservice.model;

import java.util.Date;

public class Booking {
    private final Date startTime;
    private final Date endTime;

    public Booking(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public boolean overlaps(Date startTime, Date endTime) {
        return !(this.endTime.before(startTime) || this.startTime.after(endTime));
    }
}

