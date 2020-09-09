package com.example.demo.models;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestSignUpModel {
	
	@NotNull(message="Username cannot be null")
	@Size(min=5, message= "Username must not be less than five characters")
	private String userName;   
	
	@NotNull(message="Password cannot be null")
	@Size(min=8, max=16, message="Password must be equal or grater than 8 characters and less than 16 characters")
    private String password;   
	
	@NotNull(message="Email cannot be null")
	@Email
    private String email;   
	
	@NotNull(message="MobileNumber cannot be null")
	@Size(min=10, max=10, message= "MobileNumber must be 10 characters")
    private String mobileNumber;
    
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
    

}
