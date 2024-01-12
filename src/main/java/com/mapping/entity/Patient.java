package com.mapping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Patient_Details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Patient_Id")
	private Long patientId;
	
	@Column(name="First_Name")
	private String firstName;
	
	@Column(name="Last_Name")
	private String lastName;
	
	@Column(name="Age")
	private Integer age;
	
	@Column(name="Email_Address")
	private String email;
	
	@Column(name="Mobile_Number")
	private Long phoneNumber;
	
	@Column(name="Diagnosis")
	private String diagnosis;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Doctor_Id")
	@JsonIgnore
	private Doctor doctor;
	
}
