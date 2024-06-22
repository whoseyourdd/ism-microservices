package com.whoseyourdd.ism.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoseyourdd.ism.student.dto.RequestEnrollStudentToClassroomDto;
import com.whoseyourdd.ism.student.dto.StudentDto;
import com.whoseyourdd.ism.student.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

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

	@GetMapping("/classrooms/{id}")
	public ResponseEntity<?> findStudentsByClassroomId(@PathVariable int id){
		try {
			List<StudentDto> students = studentService.findStudentsByClassroomId(id);
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<?> findStudentById(@PathVariable int id){
		try{
			StudentDto student = studentService.findStudentById(id);
			return new ResponseEntity<>(student,HttpStatus.OK);
		}catch(Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
	}

	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto, HttpServletRequest request){
    try {
      StudentDto student = studentService.addStudent(studentDto);
      return new ResponseEntity<>(student, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
  }

	@PutMapping
	public ResponseEntity<?> updateStudent(StudentDto studentDto, HttpServletRequest request){
		try{
			StudentDto updatedStudent = studentService.updateStudent(studentDto);
      return new ResponseEntity<>(updatedStudent,HttpStatus.OK);
		}catch(Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id, HttpServletRequest request){
    try{
      studentService.deleteStudent(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/enroll")
	public ResponseEntity<?> enrollStudentToClassroom(@RequestBody RequestEnrollStudentToClassroomDto enrollStudentDto, HttpServletRequest request){
    try{
      StudentDto enrolledStudent = studentService.enrollStudentToClassroom(enrollStudentDto);
			return new ResponseEntity<>(enrolledStudent,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
