package com.whoseyourdd.ism.student.service;

import java.util.List;

import com.whoseyourdd.ism.student.dto.RequestEnrollStudentToClassroomDto;
import com.whoseyourdd.ism.student.dto.StudentDto;

/**
 * StudentService
 */
public interface StudentService {

	List<StudentDto> findAllStudents() throws Exception;
	StudentDto findStudentById(int id) throws Exception;
	StudentDto addStudent(StudentDto student) throws Exception;
  StudentDto updateStudent(StudentDto student) throws Exception;
  void deleteStudent(int id) throws Exception;

	List<StudentDto> findStudentsByClassroomId(int classroomId) throws Exception;
	StudentDto enrollStudentToClassroom(RequestEnrollStudentToClassroomDto enrollStudetDto) throws Exception;;
}
