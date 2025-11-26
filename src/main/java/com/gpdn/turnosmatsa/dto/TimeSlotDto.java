package com.gpdn.turnosmatsa.dto;


import java.time.LocalTime;

public class TimeSlotDto {
    private LocalTime time;
    private boolean available;

    public TimeSlotDto(LocalTime time, boolean available) {
        this.time = time;
        this.available = available;
    }

    public LocalTime getTime() { return time; }
    public boolean isAvailable() { return available; }
}

