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
import com.mapping.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController 
{
	@Autowired
	private PatientService patientService;
	
	@PostMapping("/save")
	public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient)
	{
		Patient savePatient = patientService.savePatient(patient);
		return new ResponseEntity<>(savePatient,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> fetchAllPatients()
	{
		List<Patient> allPatients = patientService.getAllPatients();
		return new ResponseEntity<>(allPatients, HttpStatus.OK);
	}
	
	@GetMapping("/{patientId}")
	public ResponseEntity<Patient> fetchPatientById(@PathVariable("patientId")Long patientId)
	{
		Patient getPatientById = patientService.getPatientById(patientId);
		return new ResponseEntity<>(getPatientById,HttpStatus.OK);
	}
	
	@PutMapping("/modify/{patientId}")
	public ResponseEntity<Patient> modifyPatient(@PathVariable("patientId")Long patientId,@RequestBody Patient patient)
	{
		Patient updatePatient = patientService.updatePatient(patientId, patient);
		return new ResponseEntity<>(updatePatient,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{patientId}")
	public ResponseEntity<String> deletePatientById(@PathVariable("patientId")Long patientId)
	{
		patientService.deletePatientById(patientId);
		return new ResponseEntity<>("Patient with Id " + patientId + "Deleted Successfully..!",HttpStatus.OK);
	}
}
