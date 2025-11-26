package com.gpdn.turnosmatsa.service;



import com.gpdn.turnosmatsa.model.StudyType;
import com.gpdn.turnosmatsa.repository.StudyTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyTypeService {

    private final StudyTypeRepository repository;

    public StudyTypeService(StudyTypeRepository repository) {
        this.repository = repository;
    }

    public List<StudyType> findAll() {
        return repository.findAll();
    }

    public StudyType getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudio no encontrado: " + id));
    }
}

