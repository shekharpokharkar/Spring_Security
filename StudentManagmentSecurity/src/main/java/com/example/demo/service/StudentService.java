package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.StudentDTO;

public interface StudentService {

	StudentDTO findById(Integer studentId);

	void deleteStudentById(Integer studentId);

	StudentDTO updateStudent(Integer studentId, StudentDTO StudentDTO);

	List<StudentDTO> findAll();

	StudentDTO saveStudent(StudentDTO student);

}
