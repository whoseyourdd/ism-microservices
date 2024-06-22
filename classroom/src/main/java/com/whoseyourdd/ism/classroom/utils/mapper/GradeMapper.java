package com.whoseyourdd.ism.classroom.utils.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whoseyourdd.ism.classroom.dto.ClassroomDto;
import com.whoseyourdd.ism.classroom.dto.GradeDto;
import com.whoseyourdd.ism.classroom.model.Classroom;
import com.whoseyourdd.ism.classroom.model.Grade;

/**
 * GradeMapper
 */
@Component
public class GradeMapper {

	@Autowired
	ClassroomMapper classroomMapper;
	
	public GradeDto ToGradeDto(Grade grade) {
		List<ClassroomDto> classrooms = new ArrayList<>();
		for(Classroom cr: grade.getClassrooms()) {
			ClassroomDto classroom = classroomMapper.ToClassroomDto(cr, grade, null);
			classrooms.add(classroom);
		}
		return GradeDto.builder()
			.gradeLevel(grade.getGradeLevel())
			.classrooms(classrooms)
			.build();
  }

	public Grade ToGradeModel(GradeDto gradeDto) {
    Grade grade = new Grade();
    grade.setGradeLevel(gradeDto.getGradeLevel());
    return grade;
  }
}
