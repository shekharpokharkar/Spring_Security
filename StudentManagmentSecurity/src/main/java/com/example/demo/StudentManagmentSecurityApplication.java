package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.StudentService;

@SpringBootApplication
public class StudentManagmentSecurityApplication implements CommandLineRunner {
	
	@Autowired
	private StudentService service;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagmentSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		

		/*
		 * StudentDTO student = new StudentDTO(); student.setStudentCity("Manchar");
		 * student.setStudentEmail("shekharpokharkar@gmail.com");
		 * student.setStudentName("shekhar"); student.setStudentPassword("shekhar123");
		 * student.setStudentUserName("SHEKHAR123");
		 * 
		 * List<RoleDTO> rlist = new ArrayList<>(); RoleDTO admin = new RoleDTO();
		 * admin.setRole("ROLE_Admin"); admin.setStudent(student);
		 * 
		 * RoleDTO user = new RoleDTO(); admin.setRole("ROLE_USER");
		 * admin.setStudent(student);
		 * 
		 * rlist.add(user); rlist.add(admin);
		 * 
		 * student.setRoles(rlist);
		 * 
		 * service.saveStudent(student);
		 */
	}

}
