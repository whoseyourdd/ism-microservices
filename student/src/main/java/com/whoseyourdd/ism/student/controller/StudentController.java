package com.whoseyourdd.ism.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoseyourdd.ism.student.dto.StudentDto;
import com.whoseyourdd.ism.student.service.StudentService;

/**
 * StudentController
 */
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public ResponseEntity<?> findAllStudents(){
		try {
			List<StudentDto> students = studentService.findAllStudents();
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/school/{id}")
	public ResponseEntity<?> findStudentsByClassroomId(@PathVariable int id){
		try {
			List<StudentDto> students = studentService.findStudentsByClassroomId(id);
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
