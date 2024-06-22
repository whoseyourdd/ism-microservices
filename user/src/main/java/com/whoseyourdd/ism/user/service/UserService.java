package com.whoseyourdd.ism.user.service;

import com.whoseyourdd.ism.user.dto.RequestRegisterDto;
import com.whoseyourdd.ism.user.dto.UserDto;

/**
 * UserService
 */
public interface UserService {
	UserDto registerUser(RequestRegisterDto requestRegisterDto) throws Exception;
	UserDto checkUserLogin(String authCreds) throws Exception;
}
