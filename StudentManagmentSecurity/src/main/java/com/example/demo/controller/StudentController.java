package com.example.demo.controller;

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

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	
	@Autowired
	private StudentService studentService;

	@PostMapping("/")
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO student) {
		StudentDTO saveEmployee = studentService.saveStudent(student);
		return new ResponseEntity<StudentDTO>(saveEmployee, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<StudentDTO>> getAllStudent() {
		List<StudentDTO> saveEmployee = studentService.findAll();
		return new ResponseEntity<List<StudentDTO>>(saveEmployee, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Integer studentId, @RequestBody StudentDTO student) {
		StudentDTO updatedStudent = studentService.updateStudent(studentId,student);
		return new ResponseEntity<StudentDTO>(updatedStudent, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable("id") Integer studentId) {
		studentService.deleteStudentById(studentId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> findStudentById(@PathVariable("id") Integer studentId) {
		StudentDTO Student = studentService.findById(studentId);
		return new ResponseEntity<StudentDTO>(Student, HttpStatus.OK);
	}
}
