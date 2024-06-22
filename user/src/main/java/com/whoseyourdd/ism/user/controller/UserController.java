package com.whoseyourdd.ism.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoseyourdd.ism.user.dto.RequestRegisterDto;
import com.whoseyourdd.ism.user.dto.UserDto;
import com.whoseyourdd.ism.user.service.UserService;

/**
 * UserController
 */
@RestController
@RequestMapping("/api/public")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> signup(@RequestBody RequestRegisterDto requestRegisterDto) {
		try{
			UserDto user = userService.registerUser(requestRegisterDto);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
  }
  
  @GetMapping("/users/{authCreds}")
  public ResponseEntity<?> checkUserLogin(@PathVariable String authCreds) {
		try{
			UserDto user = userService.checkUserLogin(authCreds);
      return new ResponseEntity<>(user,HttpStatus.OK);
		}catch(Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
  }
	
}
