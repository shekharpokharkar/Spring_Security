package com.example.demo.enitity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Student_Role")
public class Role {

	@Column(name = "Roles")
	private String role;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentRoleId;

	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "studentID")
	private Student student;

}
