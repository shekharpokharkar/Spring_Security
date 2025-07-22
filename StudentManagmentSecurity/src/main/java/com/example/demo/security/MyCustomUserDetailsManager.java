package com.example.demo.security;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import com.example.demo.enitity.MyStudentUser;
import com.example.demo.enitity.Student;
import com.example.demo.repository.StudentRepository;

public class MyCustomUserDetailsManager implements UserDetailsManager {

	@Autowired
	private StudentRepository repo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Student student = repo.findByStudentUserName(username)
				.orElseThrow(() -> new RuntimeException("Student Not Found"));

		UserDetails user = new MyStudentUser(student);

		return user;
	}

	@Override
	public void createUser(UserDetails user) {

		MyStudentUser student = (MyStudentUser) user;
		Student studentUser = student.getStudent();

		repo.save(studentUser);

	}

	@Override
	public void updateUser(UserDetails user) {
		MyStudentUser mystudentUser = (MyStudentUser) user;

		Student student = mystudentUser.getStudent();
		Student oldstudent = repo.findByStudentUserName(student.getStudentUserName())
				.orElseThrow(() -> new RuntimeException("Student Not Found"));

		BeanUtils.copyProperties(student, oldstudent);

		oldstudent.setStudentPassword(encoder.encode(student.getStudentPassword()));

		Student save = repo.save(oldstudent);
		System.out.println(save);
	}

	@Override
	public void deleteUser(String username) {
		Student student = repo.findByStudentUserName(username)
				.orElseThrow(() -> new RuntimeException("Student Not Found"));
		repo.delete(student);

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Student student = repo.findByStudentUserName(authentication.getName())
				.orElseThrow(() -> new RuntimeException("Student Not Found"));

		boolean isMatches = encoder.matches(oldPassword, student.getStudentPassword());

		if (!isMatches) {
			throw new RuntimeException("old Password you entered is not matches with database password");
		}

		String encode = encoder.encode(newPassword);

		int updatePasswordByUsername = repo.updatePasswordByUsername(authentication.getName(), encode);

		if (updatePasswordByUsername == 1) {
			System.out.println("Password changes successfully");
		}

	}

	@Override
	public boolean userExists(String username) {

		return repo.findByStudentUserName(username).isPresent();
	}

}
