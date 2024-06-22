package com.whoseyourdd.ism.classroom.controller;

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

import com.whoseyourdd.ism.classroom.dto.ClassroomDto;
import com.whoseyourdd.ism.classroom.service.ClassroomService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * ClassroomController
 */
@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {

	@Autowired
	private ClassroomService classroomService;
	
	@GetMapping
	public ResponseEntity<?> findAllClassroom(){
		try{
			List<ClassroomDto> classrooms = classroomService.findAllClassrooms();
			return new ResponseEntity<>(classrooms,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findClassroomById(@PathVariable int id){
    try{
      ClassroomDto classroom = classroomService.findClassroomById(id);
      return new ResponseEntity<>(classroom,HttpStatus.OK);
    }catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<?> createClassroom(@RequestBody ClassroomDto classroomDto, HttpServletRequest request){
		try{
			ClassroomDto classroom = classroomService.addClassroom(classroomDto);
			return new ResponseEntity<>(classroom,HttpStatus.OK);
		}catch(Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
  }

	@PutMapping
	public ResponseEntity<?> updateClassroom(@RequestBody ClassroomDto classroomDto, HttpServletRequest request){
		try{
      ClassroomDto classroom = classroomService.updateClassroom(classroomDto);
      return new ResponseEntity<>(classroom,HttpStatus.OK);
    }catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClassroom(@PathVariable int id, HttpServletRequest request){
    try{
			classroomService.deleteClassroom(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
	}
	
}
