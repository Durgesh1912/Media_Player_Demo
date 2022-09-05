package com.uploadvideo.service;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.management.RuntimeErrorException;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uploadvideo.dto.UserInfoDTO;
import com.uploadvideo.model.UserDto;
import com.uploadvideo.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserRegistrationService {

	@Value("${auth0.userinfoEndpoint}")
	private String userInfoEndpoint;
	
	@Autowired
    UserRepository userRepository;

    public void registerUser(String tokenValue) {
       
    	HttpRequest httprequest=HttpRequest.newBuilder()
    	.GET()
    	.uri(URI.create(userInfoEndpoint))
    	.setHeader("Authorization",String.format("Bearer %s", tokenValue))
    	.build();
    	
    	HttpClient httpclinet=HttpClient.newBuilder()
    	.version(Version.HTTP_2)
    	.build();
    	
    	try {
    	HttpResponse<String> httpresponse=httpclinet.send(httprequest, HttpResponse.BodyHandlers.ofString());
    	String body=httpresponse.body();
    	
    	ObjectMapper objectmapper=new ObjectMapper();
    	objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
    	
    	UserInfoDTO userInfodto=objectmapper.readValue(body, UserInfoDTO.class);
    	
    	UserDto user=new UserDto();
    	user.setFirstName(userInfodto.getGivenName());
    	user.setLastName(userInfodto.getFamilyName());
    	user.setEmailAddress(userInfodto.getEmail());
    	
    	userRepository.save(user);
    	
    	
    	}
    	catch(Exception e) {
    		throw new RuntimeException("Exception occured while registering user", e);
    	}
    }
}

