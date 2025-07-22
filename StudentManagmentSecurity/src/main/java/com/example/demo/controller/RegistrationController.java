package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ChangePasswordDTO;
import com.example.demo.enitity.MyStudentUser;
import com.example.demo.enitity.Role;
import com.example.demo.enitity.Student;
import com.example.demo.security.MyCustomUserDetailsManager;

@RestController
public class RegistrationController {

	@Autowired
	private MyCustomUserDetailsManager manager;

	@PostMapping("/register")
	public ResponseEntity<String> registerStudent(@RequestBody Student student) {

		for (Role role : student.getRoles()) {
			role.setStudent(student);
		}

		MyStudentUser user = new MyStudentUser(student);

		manager.createUser(user);
		return new ResponseEntity<String>("Student Saved sucessfully", HttpStatus.OK);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<String> updateStudent(@RequestBody Student student, Authentication authentication) {

		for (Role role : student.getRoles()) {
			role.setStudent(student);
		}

		MyStudentUser user = new MyStudentUser(student);

		manager.updateUser(user);
		return new ResponseEntity<String>("Student Saved sucessfully", HttpStatus.OK);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<String> changePasswordStudent(@RequestBody ChangePasswordDTO DTO) {

		if (!(DTO.getNewPassword().equals(DTO.getConfirmPassword()))) {
			throw new RuntimeException("Password Not matches");
		}
		manager.changePassword(DTO.getOldPassword(), DTO.getNewPassword());
		return new ResponseEntity<String>("Student Saved sucessfully", HttpStatus.OK);
	}

	// @PreAuthorize("authentication.name == #username or
	// hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{studentUserName}")
	public ResponseEntity<String> deleteStudent(@PathVariable("studentUserName") String userName) {

		manager.deleteUser(userName);
		return new ResponseEntity<String>("Student Saved sucessfully", HttpStatus.OK);
	}

}
