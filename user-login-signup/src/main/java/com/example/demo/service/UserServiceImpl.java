package com.example.demo.service;

import java.util.ArrayList;

//import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.models.Users;
import com.example.demo.models.UserDto;
import com.example.demo.models.UserRequestLoginModel;
import com.example.demo.models.UserRequestSignUpModel;
import com.example.demo.models.UserResponseModel;

@Service
public class UserServiceImpl implements UserService{
	private UserDao userDao;
	private ModelMapper modelMapper;
	BCryptPasswordEncoder bCryptPasswordEncoder;
    Environment environment;
    
    @Autowired
	public UserServiceImpl(UserDao userDao, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder,
			Environment environment) {
		super();
		this.userDao = userDao;
		this.modelMapper = modelMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.environment = environment;
	}

//	@Override
//	@Transactional
//	public UserResponseModel userSignUp(UserRequestSignUpModel userRequestSignUpModel) {
//		// TODO Auto-generated method stub
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		User user = modelMapper.map(userRequestSignUpModel,User.class);
//		user.setUserType("user");
//		User usernew = userDao.findByUserName(userRequestSignUpModel.getUserName());
//		if(usernew == null) {
//			user.setConfirmed("Email Id Confirmation Required");			
//	        userDao.saveAndFlush(user);	        
//		}
//		else {
//			user.setConfirmed("UserName Already Exists");			
//		}
//		return modelMapper.map(user,UserResponseModel.class);
//		
//	}

//	@Override
//	@Transactional
//	public UserResponseModel userLogin(UserRequestLoginModel userRequestLoginModel) {
//		// TODO Auto-generated method stub
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		// user = modelMapper.map(userRequestLoginModel,User.class);
//		User user = userDao.findByUserNameAndPassword(userRequestLoginModel.getUserName(), userRequestLoginModel.getPassword());
//		if (user != null) {
//			if(user.getConfirmed() == "Email Id Confirmed") {
//				return modelMapper.map(user,UserResponseModel.class);					        	        
//			}
//			else {
//				UserResponseModel userResponse = modelMapper.map(userRequestLoginModel, UserResponseModel.class);
//				userResponse.setConfirmed(user.getConfirmed());
//				return userResponse;
//			}			
//		}
//		
//		else {
//			UserResponseModel userResponse = modelMapper.map(userRequestLoginModel, UserResponseModel.class);
//			userResponse.setConfirmed("Please Sign Up");	
//			return userResponse;
//		}
//		
//	}
	
//	@Override
//	@Transactional
//	public String userLogin(UserRequestLoginModel userRequestLoginModel) {
//		// TODO Auto-generated method stub
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		// user = modelMapper.map(userRequestLoginModel,User.class);
//		User user = userDao.findByUserNameAndPassword(userRequestLoginModel.getUserName(), userRequestLoginModel.getPassword());
//		if (user != null) {
//			if(user.getConfirmed() == "Email Id Confirmed") {
//				return "Successfully Logged In";					        	        
//			}
//			else {
//				//UserResponseModel userResponse = modelMapper.map(userRequestLoginModel, UserResponseModel.class);
//				//userResponse.setConfirmed(user.getConfirmed());
//				return "Email Id Confirmation Required";
//			}			
//		}
//		
//		else {
//			//UserResponseModel userResponse = modelMapper.map(userRequestLoginModel, UserResponseModel.class);
//			//userResponse.setConfirmed("Please Sign Up");	
//			return "Please Sign Up";
//		}
//		
//	}

//	@Override
//	@Transactional
//	public String logout() {
//		// TODO Auto-generated method stub
//		return "Successfully Logged Out";
//	}

//	@Override
//	@Transactional
//	public UserResponseModel findByUserName(String userName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	@Transactional
//	public UserResponseModel updateProfile(Long id, UserRequestSignUpModel userRequestSignUpModel) {
//		// TODO Auto-generated method stub
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		User user = userDao.findById(id).get();		
//		User usernew = modelMapper.map(userRequestSignUpModel, User.class);
//		usernew.setId(user.getId());
//		usernew.setConfirmed(user.getConfirmed());
//		usernew.setUserType(user.getUserType());
//		userDao.saveAndFlush(usernew);		
//		return modelMapper.map(usernew,UserResponseModel.class);
//		
//	}

//	@Override
//	public UserResponseModel activateAccount(UserRequestLoginModel userRequestLoginModel) {
//		// TODO Auto-generated method stub
//		User user = userDao.findByUserNameAndPassword(userRequestLoginModel.getUserName(), userRequestLoginModel.getPassword());
//		user.setConfirmed("Email Id Confirmed");
//		userDao.saveAndFlush(user);
//		return modelMapper.map(user,UserResponseModel.class);
//	}
	
//	@Override
//	@Transactional
//	public String activateAccount(UserRequestLoginModel userRequestLoginModel) {
//		// TODO Auto-generated method stub
//		User user = userDao.findByUserNameAndPassword(userRequestLoginModel.getUserName(), userRequestLoginModel.getPassword());
//		user.setConfirmed("Email Id Confirmed");
//		userDao.saveAndFlush(user);
//		return "Email Id Confirmed";
//	}

//	@Override
//	@Transactional
//	public UserResponseModel getUserProfile(String userName) {
//		// TODO Auto-generated method stub
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		User user = userDao.findByUserName(userName);			
//		return modelMapper.map(user, UserResponseModel.class);	
//		
//	}

	@Override
	@Transactional
	public UserDto userSignUp(UserRequestSignUpModel userRequestSignUpModel) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		String email = userRequestSignUpModel.getEmail();
		String username = userRequestSignUpModel.getUserName();
		Users usernew = userDao.findByUserName(username);
		Users usernew1 = userDao.findByEmail(email);
		if(usernew!=null || usernew1!=null) {			
			return null;
		}
		userRequestSignUpModel.setPassword(bCryptPasswordEncoder.encode(userRequestSignUpModel.getPassword()));		
		Users user = modelMapper.map(userRequestSignUpModel,Users.class);
		user.setUserType("user");
		user.setConfirmed("Email Id Confirmation Required");
		user = userDao.save(user);	
		
		//User usernew = userDao.findByUserName(userRequestSignUpModel.getUserName());
		
//		if(usernew == null) {
//			user.setConfirmed("Email Id Confirmation Required");			
//	        userDao.saveAndFlush(user);	        
//		}
//		else {
//			user.setConfirmed("UserName Already Exists");			
//		}
		return modelMapper.map(user,UserDto.class);
		
	}

	@Override
	@Transactional
	public UserDto getUserProfile(String userName) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Users user = userDao.findByUserName(userName);
		if(user==null) {
			throw new UsernameNotFoundException(userName);
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	@Transactional
	public UserDto getUserProfileByEmail(String email) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Users user = userDao.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException(email);
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	@Transactional
	public UserDto updateProfile(Long id, UserRequestSignUpModel userRequestSignUpModel) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Users user = userDao.findById(id).orElse(null);	
		Users usernew = modelMapper.map(userRequestSignUpModel, Users.class);
		usernew.setId(user.getId());
		usernew.setConfirmed(user.getConfirmed());
		usernew.setUserType(user.getUserType());
		usernew = userDao.save(usernew);		
		return modelMapper.map(usernew, UserDto.class);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		// TODO Auto-generated method stub		
		Users user = userDao.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(user.getEmail(), user.getPassword(), true, true, true, true, new ArrayList<>());
		
	}
	
	
}
