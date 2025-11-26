package com.gpdn.turnosmatsa.service;



import com.gpdn.turnosmatsa.dto.AppointmentRequestDto;
import com.gpdn.turnosmatsa.dto.TimeSlotDto;
import com.gpdn.turnosmatsa.model.Appointment;
import com.gpdn.turnosmatsa.model.Doctor;
import com.gpdn.turnosmatsa.model.Patient;
import com.gpdn.turnosmatsa.model.StudyType;
import com.gpdn.turnosmatsa.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final StudyTypeService studyTypeService;

    // horario del centro: 9 a 17 hs, turnos de 1 hora
    private static final LocalTime OPENING_TIME = LocalTime.of(9, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(17, 0); // último que empieza 16:00

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorService doctorService,
                              PatientService patientService,
                              StudyTypeService studyTypeService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.studyTypeService = studyTypeService;
    }

    @Transactional
    public Appointment book(AppointmentRequestDto dto) {
        Doctor doctor = doctorService.getById(dto.getDoctorId());
        StudyType studyType = studyTypeService.getById(dto.getStudyTypeId());

        // Validación: el médico debe pertenecer al estudio elegido
        if (!doctor.getStudyType().getId().equals(studyType.getId())) {
            throw new IllegalArgumentException("El médico no corresponde al estudio seleccionado");
        }

        Patient patient = patientService.findOrCreate(
                dto.getPatientFirstName(),
                dto.getPatientLastName(),
                dto.getPatientInsurance(),
                dto.getPatientDocument()
        );

        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();

        if (date == null || time == null) {
            throw new IllegalArgumentException("Fecha y hora son obligatorias");
        }

        // Validar que la hora está dentro del horario
        if (time.isBefore(OPENING_TIME) || time.isAfter(CLOSING_TIME.minusHours(1))) {
            throw new IllegalArgumentException("Hora fuera del horario de atención");
        }

        LocalDateTime start = LocalDateTime.of(date, time);
        LocalDateTime end = start.plusHours(1);

        // Reglas de negocio clave:
        if (appointmentRepository.existsByDoctorAndStartDateTime(doctor, start)) {
            throw new IllegalStateException("El médico ya tiene un turno en ese horario");
        }

        if (appointmentRepository.existsByPatientAndStartDateTime(patient, start)) {
            throw new IllegalStateException("El paciente ya tiene un turno en ese horario");
        }

        Appointment appt = new Appointment();
        appt.setDoctor(doctor);
        appt.setPatient(patient);
        appt.setStudyType(studyType);
        appt.setStartDateTime(start);
        appt.setEndDateTime(end);

        return appointmentRepository.save(appt);
    }

    public List<TimeSlotDto> getAvailability(Long doctorId, LocalDate date) {
        // busca todos los turnos del médico para ese día
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime to = date.plusDays(1).atStartOfDay();

        List<Appointment> appointments =
                appointmentRepository.findByDoctorIdAndStartDateTimeBetween(doctorId, from, to);

        Set<LocalTime> bookedTimes = new HashSet<>();
        for (Appointment a : appointments) {
            bookedTimes.add(a.getStartDateTime().toLocalTime());
        }

        List<TimeSlotDto> slots = new ArrayList<>();
        LocalTime t = OPENING_TIME;
        while (!t.isAfter(CLOSING_TIME.minusHours(1))) {
            boolean available = !bookedTimes.contains(t);
            slots.add(new TimeSlotDto(t, available));
            t = t.plusHours(1);
        }
        return slots;
    }

    // para el calendario: días que aún tienen al menos un turno disponible entre dos fechas
    public List<LocalDate> getAvailableDays(Long doctorId, LocalDate from, LocalDate to) {
        List<LocalDate> days = new ArrayList<>();
        LocalDate d = from;
        while (!d.isAfter(to)) {
            boolean anyAvailable = getAvailability(doctorId, d)
                    .stream().anyMatch(TimeSlotDto::isAvailable);
            if (anyAvailable) {
                days.add(d);
            }
            d = d.plusDays(1);
        }
        return days;
    }
}

