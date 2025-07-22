package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StudentDTO {

	@JsonProperty("StudentId")
	private Integer studentId;
	@JsonProperty("Student_Name")
	private String studentName;
	@JsonProperty("Student_City")
	private String studentCity;
	@JsonProperty("Student_Email")
	private String studentEmail;
	@JsonProperty("Student_User_Name")
	private String studentUserName;
	@JsonProperty("Student_Password")
	private String studentPassword;
	@JsonIgnore
	@JsonProperty("Student_Roles")
	private List<RoleDTO> roles;
}
