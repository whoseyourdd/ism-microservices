package com.whoseyourdd.ism.classroom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HealthController
 */
@RestController
@RequestMapping("/classroom/health")
public class HealthController {

	@GetMapping
	public String getHealth() {
    return "OK";
  }
	
}
