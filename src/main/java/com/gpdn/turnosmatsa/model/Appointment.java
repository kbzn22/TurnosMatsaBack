package com.gpdn.turnosmatsa.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "appointment",
        uniqueConstraints = {
                // no se puede asignar el mismo turno (doctor + fecha/hora) a 2 pacientes
                @UniqueConstraint(name = "uk_doctor_datetime",
                        columnNames = {"doctor_id", "start_datetime"}),
                // un paciente no puede tener 2 turnos el mismo día a la misma hora
                @UniqueConstraint(name = "uk_patient_datetime",
                        columnNames = {"patient_id", "start_datetime"})
        }
)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "study_type_id")
    private StudyType studyType;

    @Column(name = "start_datetime", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_datetime", nullable = false)
    private LocalDateTime endDateTime;

    // podría agregarse estado (RESERVADO, CANCELADO, etc.)
    // ...

    // getters/setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public StudyType getStudyType() { return studyType; }
    public void setStudyType(StudyType studyType) { this.studyType = studyType; }

    public LocalDateTime getStartDateTime() { return startDateTime; }
    public void setStartDateTime(LocalDateTime startDateTime) { this.startDateTime = startDateTime; }

    public LocalDateTime getEndDateTime() { return endDateTime; }
    public void setEndDateTime(LocalDateTime endDateTime) { this.endDateTime = endDateTime; }
}

