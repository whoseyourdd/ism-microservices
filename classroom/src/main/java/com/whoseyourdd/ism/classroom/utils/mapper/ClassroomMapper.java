package com.whoseyourdd.ism.classroom.utils.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.whoseyourdd.ism.classroom.dto.ClassroomDto;
import com.whoseyourdd.ism.classroom.dto.StudentDto;
import com.whoseyourdd.ism.classroom.dto.TeacherDto;
import com.whoseyourdd.ism.classroom.model.Classroom;
import com.whoseyourdd.ism.classroom.model.Grade;

/**
 * ClassroomMapper
 */
@Component
public class ClassroomMapper {
	
	public ClassroomDto ToClassroomDto(Classroom classroom, Grade grade, List<StudentDto> students, TeacherDto teacher) {
		return ClassroomDto.builder()
			.classroomId(classroom.getClassroomId())
			.classroomName(classroom.getClassroomName())
			.gradeLevelId(grade.getGradeId())
			.gradeLevel(grade.getGradeLevel())
			.students(students)
			.homeroomTeacher(teacher)
      .build();
  }

	public Classroom ToClassroomModel(ClassroomDto classroom) {
		System.out.println(classroom);
    return Classroom.builder()
      .classroomId(classroom.getClassroomId())
      .classroomName(classroom.getClassroomName())
			.gradeId(classroom.getGradeLevelId())
      .build();
  }
}
