package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.models.UserDto;
import com.example.demo.models.UserRequestLoginModel;
import com.example.demo.models.UserRequestSignUpModel;
import com.example.demo.models.UserResponseModel;

public interface UserService extends UserDetailsService{
	////public UserResponseModel userSignUp(UserRequestSignUpModel userRequestSignUpModel);
	public UserDto userSignUp(UserRequestSignUpModel userRequestSignUpModel);
	//public UserResponseModel userLogin(UserRequestLoginModel userRequestLoginModel);
	////public String userLogin(UserRequestLoginModel userRequestLoginModel);
	////public String logout();	
	//public List<UserResponseModel> getAllUsers();
	////public UserResponseModel getUserProfile(String userName);
	public UserDto getUserProfile(String userName);
	public UserDto getUserProfileByEmail(String email);
	
	////public UserResponseModel updateProfile(Long id, UserRequestSignUpModel userRequestSignUpModel);
	public UserDto updateProfile(Long id, UserRequestSignUpModel userRequestSignUpModel);
	//public UserResponseModel activateAccount(UserRequestLoginModel userRequestLoginModel);
	////public String activateAccount(UserRequestLoginModel userRequestLoginModel);
	public UserDetails loadUserByUsername(String email);

}
