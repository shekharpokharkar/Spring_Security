package com.example.demo.enitity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;


@Data
public class MyStudentUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer studentId;
	private String studentName;
	private String studentCity;
	private String studentEmail;
	private String studentUserName;
	private String studentPassword;
	private List<Role> roles;

	private Student student;

	public MyStudentUser(Student student) {
		super(student.getStudentUserName(), student.getStudentPassword(), mapRolesToAuthorities(student.getRoles()));

		// set custom fields
		this.student = student;
		this.studentId = student.getStudentId();
		this.studentName = student.getStudentName();
		this.studentCity = student.getStudentCity();
		this.studentEmail = student.getStudentEmail();
		this.studentUserName = student.getStudentUserName();
		this.studentPassword = student.getStudentPassword();
		this.roles = student.getRoles();
	}

	private static List<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authorities;
	}

	public MyStudentUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	
	}

	public MyStudentUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}

	
	
}
