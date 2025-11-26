package com.gpdn.turnosmatsa.dto;



import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Para modificar un turno existente.
 * Por ahora solo permitimos cambiar fecha y hora.
 */
public class AppointmentUpdateDto {

    private LocalDate date;
    private LocalTime time;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}

