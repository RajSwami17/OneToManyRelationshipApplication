package com.mapping.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Doctor_Details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Doctor_Id")
	private Long doctorId;
	
	@Column(name="Doctor_Name")
	private String name;
	
	@Column(name="Email_Address")
	private String email;
	
	@Column(name="Mobile_Number")
	private Long phoneNumber;
	
	@Column(name="Specialization")
	private String specialization;
	
	@OneToMany(mappedBy="doctor",cascade=CascadeType.ALL)
	private List<Patient> patients= new ArrayList<>();
}
