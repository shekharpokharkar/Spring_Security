package com.example.demo.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

	private String userName;
	private String token;
	private List<String> roles;

}
