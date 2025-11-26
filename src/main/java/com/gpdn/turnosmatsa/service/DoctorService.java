package com.gpdn.turnosmatsa.service;



import com.gpdn.turnosmatsa.model.Doctor;
import com.gpdn.turnosmatsa.model.StudyType;
import com.gpdn.turnosmatsa.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository repository;
    private final StudyTypeService studyTypeService;

    public DoctorService(DoctorRepository repository, StudyTypeService studyTypeService) {
        this.repository = repository;
        this.studyTypeService = studyTypeService;
    }

    public List<Doctor> findByStudyType(Long studyTypeId) {
        StudyType type = studyTypeService.getById(studyTypeId);
        return repository.findByStudyType(type);
    }

    public Doctor getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado: " + id));
    }
}

