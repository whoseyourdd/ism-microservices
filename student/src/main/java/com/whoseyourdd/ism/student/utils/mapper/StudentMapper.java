package com.whoseyourdd.ism.student.utils.mapper;

import org.springframework.stereotype.Component;

import com.whoseyourdd.ism.student.dto.ClassroomDto;
import com.whoseyourdd.ism.student.dto.StudentDto;
import com.whoseyourdd.ism.student.dto.TeacherDto;
import com.whoseyourdd.ism.student.model.Student;

/**
 * StudentMapper
 */
@Component
public class StudentMapper {

	public StudentDto ToStudentDto(Student student, ClassroomDto classroomDto) {
		return StudentDto.builder()
			.studentId(student.getStudentId())
			.studentName(student.getStudentName())
			.studentDob(student.getStudentDob())
			.studentAddress(student.getStudentAddress())
			.studentEmail(student.getStudentEmail())
			.studentGender(student.getStudentGender())
			.classroom(classroomDto)
			.build();
	}	

	public Student ToStudentModel(StudentDto studentDto) {
    return Student.builder()
      .studentId(studentDto.getStudentId())
      .studentName(studentDto.getStudentName())
      .studentDob(studentDto.getStudentDob())
      .studentAddress(studentDto.getStudentAddress())
      .studentEmail(studentDto.getStudentEmail())
      .studentGender(studentDto.getStudentGender())
      .build();
  }
}
