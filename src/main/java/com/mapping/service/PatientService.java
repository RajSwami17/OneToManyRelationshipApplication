package com.mapping.service;

import java.util.List;

import com.mapping.entity.Patient;

public interface PatientService 
{
	public Patient savePatient(Patient patient);
	
	public List<Patient> getAllPatients();
	
	public Patient getPatientById(Long patientId);
	
	public Patient updatePatient(Long patientId, Patient patient);
	
	public String deletePatientById(Long patientId);
}
