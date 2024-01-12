package com.mapping.service;

import java.util.List;

import com.mapping.entity.Doctor;

public interface DoctorService 
{
	public Doctor saveDoctor(Doctor doctor);
	
	public List<Doctor> getAllDoctors();
	
	public Doctor getDoctorById(Long doctorId);
	
	public Doctor updateDoctor(Long doctorId, Doctor doctor);
	
	public String deleteDoctorById(Long doctorId);
}
