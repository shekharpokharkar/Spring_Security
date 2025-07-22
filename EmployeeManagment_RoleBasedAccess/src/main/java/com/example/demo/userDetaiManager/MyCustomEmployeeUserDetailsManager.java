package com.example.demo.userDetaiManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import com.example.demo.entity.Employee;
import com.example.demo.entity.MyEmployeeUserDetails;
import com.example.demo.repository.EmployeeRepository;

public class MyCustomEmployeeUserDetailsManager implements UserDetailsManager {

	@Autowired
	public PasswordEncoder encoder;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee employee = employeeRepository.findByUserName(username)
				.orElseThrow(() -> new RuntimeException("Employee Not found"));

		MyEmployeeUserDetails userDetails = new MyEmployeeUserDetails(employee);

		return userDetails;
	}

	@Override
	public void createUser(UserDetails user) {
		MyEmployeeUserDetails userDetails = (MyEmployeeUserDetails) user;

		Employee employee = userDetails.getEmployee();
		employee.setPassword(encoder.encode(employee.getPassword()));
		Employee employee2 = employeeRepository.save(employee);
		System.out.println("Employee Saved:" + employee2);
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
