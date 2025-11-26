package com.gpdn.turnosmatsa.controller;



import com.gpdn.turnosmatsa.model.Doctor;
import com.gpdn.turnosmatsa.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    // médicos para un tipo de estudio
    @GetMapping("/by-study/{studyTypeId}")
    public List<Doctor> getByStudyType(@PathVariable Long studyTypeId) {
        return service.findByStudyType(studyTypeId);
    }
}
