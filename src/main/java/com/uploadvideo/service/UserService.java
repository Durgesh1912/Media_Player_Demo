package com.uploadvideo.service;

import java.util.List;
import java.util.Optional;

import com.uploadvideo.model.UserDto;



public interface UserService {

	public UserDto createUser(UserDto userDto);
	
	public UserDto getUserDetails(Long userId);
	
	public UserDto updateUser(UserDto userDto);
	
	public void deleteUser(Long userId);
	
	public Optional<UserDto> findByEmailAddress(String email);
	
	public Optional<List<UserDto>> userList();

	

}
