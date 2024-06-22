package com.whoseyourdd.ism.classroom.service;

import java.util.List;

import com.whoseyourdd.ism.classroom.dto.ClassroomDto;

/**
 * ClassroomService
 */
public interface ClassroomService {

	List<ClassroomDto> findAllClassrooms() throws Exception;
	List<ClassroomDto> findClassroomByGrade(int gradeId) throws Exception;
	ClassroomDto findClassroomById(int id) throws Exception;
	ClassroomDto addClassroom(ClassroomDto classroomDto) throws Exception;

  void deleteClassroom(int id) throws Exception;
}
