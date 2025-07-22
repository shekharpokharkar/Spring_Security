package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {

	@JsonProperty("ROLE")
	private String role;

	@JsonProperty("Student_Role_Id")
	private Integer studentRoleId;

	@JsonProperty("studentDTO")
	private StudentDTO student;

}
