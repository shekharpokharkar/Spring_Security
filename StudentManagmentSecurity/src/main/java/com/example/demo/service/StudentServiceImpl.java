package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RoleDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.enitity.Role;
import com.example.demo.enitity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentDTO findById(Integer studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("No StudentFound"));

		StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
		return studentDTO;
	}

	@Override
	public void deleteStudentById(Integer studentId) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("No StudentFound"));
		studentRepository.delete(student);
	}

	@Override
	public StudentDTO updateStudent(Integer studentId, StudentDTO student1) {
		Student oldstudent = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("No StudentFound"));

		BeanUtils.copyProperties(student1, oldstudent);
		Student save = studentRepository.save(oldstudent);
		return mapper.map(save, StudentDTO.class);
	}

	@Override
	public List<StudentDTO> findAll() {
		List<Student> all = studentRepository.findAll();
		StudentDTO[] map = mapper.map(all, StudentDTO[].class);
		return Arrays.asList(map);
	}

	@Override
	public StudentDTO saveStudent(StudentDTO student) {

		Student stu = new Student();
		ArrayList<Role> elist = new ArrayList<>();
		for (RoleDTO role : student.getRoles()) {
			Role role1 = new Role();
			BeanUtils.copyProperties(role, role1);
			role1.setStudent(stu);
			elist.add(role1);
		}

		BeanUtils.copyProperties(student, stu);
		stu.setRoles(elist);
		Student save = studentRepository.save(stu);
		return mapper.map(save, StudentDTO.class);
	}

}
