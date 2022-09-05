package com.uploadvideo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uploadvideo.exception.CustomException;
import com.uploadvideo.model.UserDto;
import com.uploadvideo.service.UserRegistrationService;
import com.uploadvideo.service.UserService;



@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//aadded for register while logging
	@Autowired
	private UserRegistrationService userRegistrationservice;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto , BindingResult result) {
		logger.info("Enter in the createUser Method");
		UserDto userDetails=null;
		try {
			if(result.hasErrors()) {
			List<ObjectError> list=	result.getAllErrors();
			for(ObjectError objectError:list) {
				return new ResponseEntity<>(objectError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			}
			}
			logger.info("user Detail Request:{}"+userDto);

		Optional<UserDto> userDtoDetails=userService.findByEmailAddress(userDto.getEmailAddress());
		if(!userDtoDetails.isPresent()) {
		 userDetails = userService.createUser(userDto);
		}
		}catch(Exception e) {
			logger.info("Exception :{}"+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>(userDetails, HttpStatus.OK);

	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable(required = true) Long userId) {
		logger.info("Enter in the getUser Method");
		UserDto userDetails=null;
		try {
			logger.info("user ID:{}"+userId);
    	    userDetails = userService.getUserDetails(userId);
			logger.info("user List Details:{}"+userDetails);

       }catch(Exception e) {
			logger.info("Exception :{}"+e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);  
       }
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}

	@GetMapping("/User-List")
	public ResponseEntity<?> getUserList() {
		logger.info("Enter in the getUserList Method");
		Optional<List<UserDto>> userDetails=null; 
		try {
		 userDetails = userService.userList();
			logger.info("user List:{}"+userDetails);

       }catch(Exception e) {
			logger.info("Exception :{}"+e);
   		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);  
        	   
    	   
       }
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}

	
	
	@PutMapping
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto) {

		if (null == userDto.getId() || userDto.getId() <= 0) {
			throw new CustomException("Invalid Id");
		}

		UserDto userDetails = userService.updateUser(userDto);

		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable(required = true) Long userId) {

		logger.info("Enetr in the deleteUser method");
		try {
			logger.info("User id : {}"+userId);
			if (userId <= 0) {
				throw new CustomException("Invalid Id");
			}
			userService.deleteUser(userId);
			logger.info("User Delete Successfully.");

		} catch (Exception e) {
			logger.info("Exception :{}"+e);

			throw new CustomException("Error while Deleting Record");
		}
		Map<String,Object> map=new HashMap<>();
		map.put("message", "Record Deleted ID :: "+userId);
		return new ResponseEntity<Map>(map,HttpStatus.OK);
	}
	
	//added to save details while login
	@GetMapping("/register")
	public String register(Authentication authentication) {
		
		Jwt jwtToken=(Jwt)authentication.getPrincipal();
		userRegistrationservice.registerUser(jwtToken.getTokenValue());
		return "user registration successfull";
	}
}
