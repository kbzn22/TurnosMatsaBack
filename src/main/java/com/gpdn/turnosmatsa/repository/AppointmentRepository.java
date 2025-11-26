package com.gpdn.turnosmatsa.repository;



import com.gpdn.turnosmatsa.model.Appointment;
import com.gpdn.turnosmatsa.model.Doctor;
import com.gpdn.turnosmatsa.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorAndStartDateTime(Doctor doctor, LocalDateTime startDateTime);

    boolean existsByPatientAndStartDateTime(Patient patient, LocalDateTime startDateTime);

    List<Appointment> findByDoctorIdAndStartDateTimeBetween(
            Long doctorId,
            LocalDateTime from,
            LocalDateTime to
    );
    // ⬇⬇⬇ NUEVOS: para usar en update
    boolean existsByDoctorAndStartDateTimeAndIdNot(
            Doctor doctor, LocalDateTime startDateTime, Long id
    );

    boolean existsByPatientAndStartDateTimeAndIdNot(
            Patient patient, LocalDateTime startDateTime, Long id
    );
}

