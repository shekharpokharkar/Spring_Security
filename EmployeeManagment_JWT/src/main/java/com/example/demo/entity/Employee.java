package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Employee_Managment")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 1. Primary Key

	private String firstName; // 2. First Name
	private String lastName; // 3. Last Name
	private String email; // 4. Email
	private String department; // 5. Department
	private double salary; // 6. Salary

	@Column(unique = true, length = 15)
	private String userName;
	@Column(length = 75)
	private String password;
	private String role;

	public Employee() {
	}

}
