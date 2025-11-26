package com.gpdn.turnosmatsa.repository;


import com.gpdn.turnosmatsa.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByDocumentNumber(String documentNumber);

}

