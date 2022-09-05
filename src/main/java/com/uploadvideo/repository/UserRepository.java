package com.uploadvideo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uploadvideo.model.UserDto;



@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {

	Optional<UserDto> findByEmailAddress(String emailAddress);


	
}
