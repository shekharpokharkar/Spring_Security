package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enitity.Student;

public interface RoleRepository extends JpaRepository<Student, Integer> {

}
