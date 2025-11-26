package com.gpdn.turnosmatsa.controller;



import com.gpdn.turnosmatsa.dto.AppointmentRequestDto;
import com.gpdn.turnosmatsa.dto.TimeSlotDto;
import com.gpdn.turnosmatsa.model.Appointment;
import com.gpdn.turnosmatsa.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Appointment> book(@RequestBody AppointmentRequestDto dto) {
        Appointment created = service.book(dto);
        return ResponseEntity.ok(created);
    }

    // Horarios disponibles para un médico y un día
    @GetMapping("/availability/{doctorId}")
    public List<TimeSlotDto> getAvailability(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getAvailability(doctorId, date);
    }

    // Días con al menos un turno libre entre 2 fechas (para marcar el calendario)
    @GetMapping("/available-days/{doctorId}")
    public List<LocalDate> getAvailableDays(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return service.getAvailableDays(doctorId, from, to);
    }
}

