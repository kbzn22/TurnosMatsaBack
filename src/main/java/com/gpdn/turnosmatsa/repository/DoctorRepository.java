package com.gpdn.turnosmatsa.repository;

import com.gpdn.turnosmatsa.model.Doctor;
import com.gpdn.turnosmatsa.model.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByStudyType(StudyType studyType);
}

