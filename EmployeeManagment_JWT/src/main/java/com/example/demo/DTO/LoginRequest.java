package com.example.demo.DTO;

import lombok.Data;

@Data
public class LoginRequest {

	private String userName;
	private String password;
	public LoginRequest() {
		
	}

}
