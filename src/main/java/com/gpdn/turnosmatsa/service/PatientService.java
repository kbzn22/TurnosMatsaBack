package com.gpdn.turnosmatsa.service;

import com.gpdn.turnosmatsa.model.Patient;
import com.gpdn.turnosmatsa.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient findOrCreate(String firstName,
                                String lastName,
                                String insuranceName,
                                String documentNumber) {

        if (documentNumber != null && !documentNumber.isBlank()) {
            return repository.findByDocumentNumber(documentNumber)
                    .orElseGet(() -> {
                        Patient p = new Patient();
                        p.setFirstName(firstName);
                        p.setLastName(lastName);
                        p.setInsuranceName(insuranceName);
                        p.setDocumentNumber(documentNumber);
                        return repository.save(p);
                    });
        }

        // Sin documento: lo creamos siempre (simplificación)
        Patient p = new Patient();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setInsuranceName(insuranceName);
        p.setDocumentNumber(documentNumber);
        return repository.save(p);
    }
}
