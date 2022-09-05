package com.uploadvideo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uploadvideo.exception.CustomException;
import com.uploadvideo.model.UserDto;
import com.uploadvideo.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserDto createUser(UserDto userDto) {
		logger.info("Enter In the createUser method");
		UserDto user=userRepository.save(userDto);
		logger.info("User Save Sucessfuly....");
		return user;
	}

	@Override
	public Optional<UserDto> findByEmailAddress(String email) {
		Optional<UserDto> userDtoDetail =userRepository.findByEmailAddress(email);
		if(userDtoDetail.isPresent()) {
			throw new CustomException("User Alredy Exits....");
		}
		return userDtoDetail;
	}

	
	@Override
	public UserDto getUserDetails(Long userId) {
		Optional<UserDto> userDetails=userRepository.findById(userId);
		
		if(!userDetails.isPresent()) {
			throw new CustomException("No Record Found");
		}
		return userDetails.get();
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		try {
		UserDto userObj=userRepository.findById(userDto.getId()).get();
		
		if(null==userObj) {
			throw new CustomException("No Record Found");
		}
		userDto.setCreatedOn(userObj.getCreatedOn());
		return userRepository.save(userDto);
		}
		catch(Exception e) {
			throw new CustomException("Error While Updating Record");
		}
	}

	@Override
	public void deleteUser(Long userId) {
		try {
			UserDto userObj=userRepository.findById(userId).get();
			
			if(userObj==null) {
				throw new CustomException("No Record Found");
			}
			
			userRepository.delete(userObj);
		}
		catch(Exception e) {
			throw new CustomException("Unable to delete Record");
		}
	}

	@Override
	public Optional<List<UserDto>> userList() {
		Optional<List<UserDto>> user = null;
		try {
			user = Optional.of(userRepository.findAll());

			if (!user.isPresent())
				throw new CustomException("No Record Found");

		} catch (Exception e) {
			throw new CustomException("Unable to Fetch Records.");

		}
		return user;
	}

}
