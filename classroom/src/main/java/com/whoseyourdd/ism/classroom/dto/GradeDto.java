package com.whoseyourdd.ism.classroom.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GradeDto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class GradeDto {
	private int gradeId;
	@JsonProperty("gradeLevel")
	private String gradeLevel;
	private List<ClassroomDto> classrooms;
}
