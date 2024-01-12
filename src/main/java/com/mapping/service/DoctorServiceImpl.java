package com.mapping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapping.entity.Doctor;
import com.mapping.entity.Patient;
import com.mapping.repo.DoctorRepo;
import com.mapping.repo.PatientRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DoctorServiceImpl implements DoctorService
{
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Override
	public Doctor saveDoctor(Doctor doctor) 
	{
		// Save the doctor to obtain the generated ID
		Doctor registerDoctor = doctorRepo.save(doctor);
		// Set the doctor reference in each patient
	    List<Patient> patients = registerDoctor.getPatients();
	    if (patients != null) 
	    {
	        for (Patient patient : patients) 
	        {
	            patient.setDoctor(registerDoctor);
	        }
	        // Save the updated patients with doctor reference
	        patientRepo.saveAll(patients);
	    }
		return registerDoctor;
	}

	@Override
	public List<Doctor> getAllDoctors() 
	{
		List<Doctor> allDoctors = doctorRepo.findAll();
		return allDoctors;
	}

	@Override
	public Doctor getDoctorById(Long doctorId)
	{
		return doctorRepo.findById(doctorId)
				.orElseThrow(()->new EntityNotFoundException("Doctor With Given Id Is Not Found" + doctorId));
	}

	@Override
	public Doctor updateDoctor(Long doctorId, Doctor updatedDoctor) 
	{
		Optional<Doctor> optional = doctorRepo.findById(doctorId);
		if(optional.isPresent())
		{
			Doctor doctor = optional.get();
			doctor.setName(updatedDoctor.getName());
			doctor.setEmail(updatedDoctor.getEmail());
			doctor.setPhoneNumber(updatedDoctor.getPhoneNumber());
			doctor.setSpecialization(updatedDoctor.getSpecialization());
			doctor.setPatients(updatedDoctor.getPatients());
			return doctorRepo.save(doctor);
		}
		else
		{
			throw new EntityNotFoundException("Doctor With Given Id Is Not Found" + doctorId);
		}
	}

	@Override
	public String deleteDoctorById(Long doctorId) 
	{
		if(doctorRepo.existsById(doctorId))
		{
			doctorRepo.deleteById(doctorId);
			return "Doctor with Id:" + doctorId + "Deleted Successfully..!!";
		}
		else
		{
			throw new EntityNotFoundException("Doctor With Given Id Is Not Found" + doctorId);
		}
	}

}
