package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.UserDto;
import com.example.demo.models.UserRequestLoginModel;
import com.example.demo.models.UserRequestSignUpModel;
import com.example.demo.models.UserResponseModel;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private Environment env;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/status/check")
	public String status()
	{
		return "Working on port " + env.getProperty("local.server.port") + ", with token = " + env.getProperty("token.secret");
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseModel> userSignUp(@RequestBody UserRequestSignUpModel userRequestSignUpModel){
		UserDto userDto = userService.userSignUp(userRequestSignUpModel);
		if(userDto==null) {
			return ResponseEntity.badRequest().body(null);
		}
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserResponseModel userResponseModel = modelMapper.map(userDto, UserResponseModel.class);
//		if(userResponseModel.getConfirmed() == "UserName Already Exists") {
//			return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.CONFLICT);
//		}
		return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.CREATED);
	}

//	@PostMapping("/login")
//	public ResponseEntity<UserResponseModel> userLogin(@RequestBody UserRequestLoginModel userRequestLoginModel){
//		UserResponseModel userResponseModel = userService.userLogin(userRequestLoginModel);
//		if(userResponseModel.getConfirmed() == "Please Sign Up") {
//			return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.NOT_FOUND);
//		}
//		else if(userResponseModel.getConfirmed() == "Email Id Confirmation Required") {
//			return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.UNAUTHORIZED);
//		}
//		return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.OK);
//	}
	
//	@PostMapping("/login")
//	public ResponseEntity<String> userLogin(@RequestBody UserRequestLoginModel userRequestLoginModel){
//		String confirmation = userService.userLogin(userRequestLoginModel);
//		if(confirmation == "Please Sign Up") {
//			return new ResponseEntity<String>(confirmation, HttpStatus.NOT_FOUND);
//		}
//		else if(confirmation == "Email Id Confirmation Required") {
//			return new ResponseEntity<String>(confirmation, HttpStatus.UNAUTHORIZED);
//		}
//		return new ResponseEntity<String>(confirmation, HttpStatus.OK);
//	}
	
//	@GetMapping("/logout")
//	public ResponseEntity<String> logout(){
//		return new ResponseEntity<String>(userService.logout(), HttpStatus.OK); 
//	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserResponseModel> updateProfile(@PathVariable Long id, @RequestBody UserRequestSignUpModel userRequestSignUpModel){
		UserDto userDto = userService.updateProfile(id, userRequestSignUpModel);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserResponseModel userResponseModel = modelMapper.map(userDto, UserResponseModel.class);
		return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.OK);
	}
	
//	@PostMapping("/activate")
//	public ResponseEntity<String> activateAccount(@RequestBody UserRequestLoginModel userRequestLoginModel){
//		return new ResponseEntity<String>(userService.activateAccount(userRequestLoginModel), HttpStatus.OK);
//	}
	
//	@GetMapping("/profile/{userName}")
//	public ResponseEntity<UserResponseModel> getUserProfile(@PathVariable String userName){
//		UserDto userDto = userService.getUserProfile(userName);
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		UserResponseModel userResponseModel = modelMapper.map(userDto, UserResponseModel.class);
//		return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.OK);
//	}
//	
//	@GetMapping("/profile/by/{email}")
//	public ResponseEntity<UserResponseModel> getUserProfileByEmail(@PathVariable String email){
//		UserDto userDto = userService.getUserProfileByEmail(email);
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		UserResponseModel userResponseModel = modelMapper.map(userDto, UserResponseModel.class);
//		return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.OK);
//	}
			

}
