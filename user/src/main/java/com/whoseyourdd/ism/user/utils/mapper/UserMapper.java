package com.whoseyourdd.ism.user.utils.mapper;

import org.springframework.stereotype.Component;

import com.whoseyourdd.ism.user.dto.RequestRegisterDto;
import com.whoseyourdd.ism.user.dto.UserDto;
import com.whoseyourdd.ism.user.model.Role;
import com.whoseyourdd.ism.user.model.User;

/**
 * UserMapper
 */
@Component
public class UserMapper {

	public static Role fromStringToEnum(String roleString) {
		if (roleString == null || roleString.isEmpty()) {
			return Role.STUDENT;
		}
		try {
			if(roleString.equalsIgnoreCase("ADMIN")){
				return Role.ADMIN;
			}else if(roleString.equalsIgnoreCase("TEACHER")){
				return Role.TEACHER;
			}
			return Role.STUDENT;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid role: " + roleString, e);
		}
	}

	public UserDto ToUserDto(User user){
		return UserDto.builder()
      .id(user.getId())
			.username(user.getUsername())
      .name(user.getName())
      .role(user.getRole())
      .build();
	}

	public User RequestRegisterToUserModel(RequestRegisterDto requestRegisterDto){
    return User.builder()
			.username(requestRegisterDto.getUsername())
			.password(requestRegisterDto.getPassword())
			.name(requestRegisterDto.getName())
			.role(requestRegisterDto.getRole() == null ? Role.STUDENT : fromStringToEnum(requestRegisterDto.getRole().name()))
			.build();
	}
}
