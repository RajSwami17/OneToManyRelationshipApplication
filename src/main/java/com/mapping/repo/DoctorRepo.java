package com.mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor,Long>{

}
