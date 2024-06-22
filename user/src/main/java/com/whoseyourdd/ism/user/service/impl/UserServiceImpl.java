package com.whoseyourdd.ism.user.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.whoseyourdd.ism.user.dto.RequestRegisterDto;
import com.whoseyourdd.ism.user.dto.UserDto;
import com.whoseyourdd.ism.user.model.User;
import com.whoseyourdd.ism.user.repository.UserRepository;
import com.whoseyourdd.ism.user.service.UserService;
import com.whoseyourdd.ism.user.utils.exception.ErrInternalException;
import com.whoseyourdd.ism.user.utils.exception.ErrNotFoundException;
import com.whoseyourdd.ism.user.utils.mapper.UserMapper;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDto registerUser(RequestRegisterDto requestRegisterDto) throws Exception{
		try{
			User user = userMapper.RequestRegisterToUserModel(requestRegisterDto);
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = passwordEncoder.encode(user.getPassword());
			System.out.println(user);
			user.setPassword(password);
			userRepository.save(user);
			return userMapper.ToUserDto(user);
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public UserDto checkUserLogin(String authCreds) throws Exception {
		try{
      String username = extractUsernameFromAuthCreds(authCreds);
			User user = userRepository.findByUsername(username).orElse(null);
			if(user == null){
				throw new ErrNotFoundException("User not found");
			}
      return userMapper.ToUserDto(user);
		}catch(ErrNotFoundException e){
			throw e;
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: " + e.getMessage());
		}
	}

	private String extractUsernameFromAuthCreds(String authCreds) throws Exception {
		try {
			byte[] credDecoded = Base64.getDecoder().decode(authCreds);
			String credentials = new String(credDecoded, StandardCharsets.UTF_8);
			final String[] values = credentials.split(":", 2);
			return values[0];
		} catch (Exception e) {
			throw new Exception("Failed to decode authentication credentials", e);
		}
	}
	
}
