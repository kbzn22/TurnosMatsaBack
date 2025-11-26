package com.gpdn.turnosmatsa.dto;



import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequestDto {

    private Long studyTypeId;
    private Long doctorId;

    private String patientFirstName;
    private String patientLastName;
    private String patientInsurance;
    private String patientDocument; // opcional

    private LocalDate date;  // día seleccionado en el calendario
    private LocalTime time;  // hora seleccionada (ej. 11:00)

    // getters/setters

    public Long getStudyTypeId() { return studyTypeId; }
    public void setStudyTypeId(Long studyTypeId) { this.studyTypeId = studyTypeId; }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public String getPatientFirstName() { return patientFirstName; }
    public void setPatientFirstName(String patientFirstName) { this.patientFirstName = patientFirstName; }

    public String getPatientLastName() { return patientLastName; }
    public void setPatientLastName(String patientLastName) { this.patientLastName = patientLastName; }

    public String getPatientInsurance() { return patientInsurance; }
    public void setPatientInsurance(String patientInsurance) { this.patientInsurance = patientInsurance; }

    public String getPatientDocument() { return patientDocument; }
    public void setPatientDocument(String patientDocument) { this.patientDocument = patientDocument; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
}

