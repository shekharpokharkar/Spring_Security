package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyEmployeeUserDetails extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Employee employee;

	public MyEmployeeUserDetails(Employee employee) {
		this(employee.getUserName(), employee.getPassword(), getRoles(employee));
		this.setEmployee(employee);
	}

	private static Collection<? extends GrantedAuthority> getRoles(Employee employee) {
		ArrayList<SimpleGrantedAuthority> alist = new ArrayList<>();
		alist.add(new SimpleGrantedAuthority(employee.getRole()));

		return alist;
	}

	public MyEmployeeUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);

	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
