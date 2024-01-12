package com.mapping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapping.entity.Patient;
import com.mapping.repo.PatientRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientServiceImpl implements PatientService
{

	@Autowired
	private PatientRepo patientRepo;
	
	@Override
	public Patient savePatient(Patient patient) 
	{
		Patient registerPatient = patientRepo.save(patient);
		return registerPatient;
	}

	@Override
	public List<Patient> getAllPatients() 
	{
		List<Patient> allPatients = patientRepo.findAll();
		return allPatients;
	}

	@Override
	public Patient getPatientById(Long patientId) 
	{
		return patientRepo.findById(patientId)
				.orElseThrow(()->new EntityNotFoundException("Patient With Given Id Is Not Found" + patientId));
	}

	@Override
	public Patient updatePatient(Long patientId, Patient updatedPatient) 
	{
		Optional<Patient> optional = patientRepo.findById(patientId);
		if(optional.isPresent())
		{
			Patient patient = optional.get();
			patient.setFirstName(updatedPatient.getFirstName());
			patient.setLastName(updatedPatient.getLastName());
			patient.setEmail(updatedPatient.getEmail());
			patient.setPhoneNumber(updatedPatient.getPhoneNumber());
			patient.setAge(updatedPatient.getAge());
			patient.setDiagnosis(updatedPatient.getDiagnosis());
			patient.setDoctor(updatedPatient.getDoctor());
			return patientRepo.save(patient);
		}
		else
		{
			throw new EntityNotFoundException("Patient With Given Id Is Not Found" + patientId);
		}
	}

	@Override
	public String deletePatientById(Long patientId) 
	{
		if(patientRepo.existsById(patientId))
		{
			patientRepo.deleteById(patientId);
			return "Patient with Id:" + patientId + "Deleted Successfully..!!";
		}
		else
		{
			throw new EntityNotFoundException("Patient With Given Id Is Not Found" + patientId);
		}
	}

}
