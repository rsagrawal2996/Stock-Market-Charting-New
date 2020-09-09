package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long id;
	private String userName;	
	private String password;
	private String userType;	
	private String email;	
	private String mobileNumber;	
	private String confirmed;

}
