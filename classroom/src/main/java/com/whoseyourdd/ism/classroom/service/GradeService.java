package com.whoseyourdd.ism.classroom.service;

import java.util.List;

import com.whoseyourdd.ism.classroom.dto.GradeDto;

/**
 * GradeService
 */
public interface GradeService {

	List<GradeDto> findAllGrades() throws Exception;
	GradeDto findGradeById(int id) throws Exception;
	GradeDto createGrade(GradeDto grade) throws Exception;
  GradeDto updateGrade(GradeDto grade) throws Exception;
  void deleteGrade(int id) throws Exception;
	
}
