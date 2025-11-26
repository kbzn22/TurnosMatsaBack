package com.gpdn.turnosmatsa.controller;



import com.gpdn.turnosmatsa.model.StudyType;
import com.gpdn.turnosmatsa.service.StudyTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-types")
@CrossOrigin(origins = "http://localhost:3000")
public class StudyTypeController {

    private final StudyTypeService service;

    public StudyTypeController(StudyTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudyType> listAll() {
        return service.findAll();
    }
}

