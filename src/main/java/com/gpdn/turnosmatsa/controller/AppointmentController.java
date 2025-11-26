package com.gpdn.turnosmatsa.controller;



import com.gpdn.turnosmatsa.dto.AppointmentRequestDto;
import com.gpdn.turnosmatsa.dto.TimeSlotDto;
import com.gpdn.turnosmatsa.model.Appointment;
import com.gpdn.turnosmatsa.service.AppointmentExportService;
import com.gpdn.turnosmatsa.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentExportService appointmentExportService;

    public AppointmentController(AppointmentService appointmentService,
                                 AppointmentExportService appointmentExportService) {
        this.appointmentService = appointmentService;
        this.appointmentExportService = appointmentExportService;
    }
    @PostMapping
    public ResponseEntity<Appointment> book(@RequestBody AppointmentRequestDto dto) {
        Appointment created = appointmentService.book(dto);
        return ResponseEntity.ok(created);
    }


    // Horarios disponibles para un médico y un día
    @GetMapping("/availability/{doctorId}")
    public List<TimeSlotDto> getAvailability(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentService.getAvailability(doctorId, date);
    }

    // Días con al menos un turno libre entre 2 fechas (para marcar el calendario)
    @GetMapping("/available-days/{doctorId}")
    public List<LocalDate> getAvailableDays(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return appointmentService.getAvailableDays(doctorId, from, to);
    }
    @GetMapping
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportToExcel() throws Exception {
        byte[] bytes = appointmentExportService.exportAllToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=turnos.xlsx");
        headers.setContentLength(bytes.length);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bytes);
    }

}

