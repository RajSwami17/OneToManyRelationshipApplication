package com.mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient,Long>{

}
