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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoseyourdd.ism.classroom.dto.GradeDto;
import com.whoseyourdd.ism.classroom.service.GradeService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * GradeController
 */
@RestController
@RequestMapping("/api/v1/grades")
public class GradeController {
	
	@Autowired
	private GradeService gradeService;

	@GetMapping
	public ResponseEntity<?> findAllGrade(){
		try{
			List<GradeDto> grades = gradeService.findAllGrades();
			return new ResponseEntity<>(grades, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findGradeById(@PathVariable int id){
    try{
      GradeDto grade = gradeService.findGradeById(id);
      return new ResponseEntity<>(grade, HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

	@PostMapping
	public ResponseEntity<?> createGrade(GradeDto grade, HttpServletRequest request){
		try{
			GradeDto newGrade = gradeService.createGrade(grade);
      return new ResponseEntity<>(newGrade, HttpStatus.CREATED);
		}catch(Exception e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	}

	@PutMapping
	public ResponseEntity<?> updateGrade(GradeDto grade, HttpServletRequest request){
    try{
      GradeDto newGrade = gradeService.updateGrade(grade);
			return new ResponseEntity<>(newGrade,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
  public ResponseEntity<?> deleteGrade(@PathVariable int id, HttpServletRequest request){
		try{
			gradeService.deleteGrade(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
	}

}
