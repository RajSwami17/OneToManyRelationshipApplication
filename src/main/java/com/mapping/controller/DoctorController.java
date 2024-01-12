package com.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.entity.Doctor;
import com.mapping.entity.Patient;
import com.mapping.service.DoctorService;
import com.mapping.service.PatientService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController 
{
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
    private PatientService patientService;
	
	@PostMapping("/save")
	public ResponseEntity<Doctor> registerDoctor(@RequestBody Doctor doctor)
	{
		Doctor saveDoctor = doctorService.saveDoctor(doctor);
		return new ResponseEntity<>(saveDoctor,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Doctor>> fetchAllDoctors()
	{
		List<Doctor> allDoctors = doctorService.getAllDoctors();
		return new ResponseEntity<>(allDoctors, HttpStatus.OK);
	}
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<Doctor> fetchDoctorById(@PathVariable("doctorId")Long doctorId)
	{
		Doctor getDoctorById = doctorService.getDoctorById(doctorId);
		return new ResponseEntity<>(getDoctorById,HttpStatus.OK);
	}
	
	@PutMapping("/modify/{doctorId}")
	public ResponseEntity<Doctor> modifyDoctor(@PathVariable("doctorId")Long doctorId,@RequestBody Doctor doctor)
	{
		Doctor updateDoctor = doctorService.updateDoctor(doctorId, doctor);
		return new ResponseEntity<>(updateDoctor,HttpStatus.OK);
	}
	
	@DeleteMapping("/{doctorId}")
	public ResponseEntity<String> deleteDoctorById(@PathVariable("doctorId")Long doctorId)
	{
		doctorService.deleteDoctorById(doctorId);
		return new ResponseEntity<>("Doctor with Id " + doctorId + "Deleted Successfully..!",HttpStatus.OK);
	}
	
	@PostMapping("/{doctorId}/addPatient")
    public ResponseEntity<Patient> addPatientToDoctor(@PathVariable Long doctorId, @RequestBody Patient patient) 
	{
        Doctor doctor = doctorService.getDoctorById(doctorId);
        patient.setDoctor(doctor);
        Patient savedPatient = patientService.savePatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }
	
	@GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<Patient>> getPatientsForDoctor(@PathVariable Long doctorId) 
	{
        Doctor doctor = doctorService.getDoctorById(doctorId);
        List<Patient> patients = doctor.getPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
	
}