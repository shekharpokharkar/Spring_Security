package com.example.demo.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {

	private String userName;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	public ChangePasswordDTO() {

	}

}
