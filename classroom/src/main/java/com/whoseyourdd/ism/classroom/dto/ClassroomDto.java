package com.whoseyourdd.ism.classroom.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassroomDto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ClassroomDto {

	private int classroomId;
	private String classroomName;
	private int gradeLevelId;
	private String gradeLevel;
	private List<StudentDto> students;
	
}
