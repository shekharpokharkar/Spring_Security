package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.enitity.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Optional<Student> findByStudentUserName(String userName);

	@Modifying
	@Transactional
	@Query("UPDATE Student s SET s.studentPassword = :newPassword WHERE s.studentUserName = :username")
	int updatePasswordByUsername(String username, String newPassword);

}
