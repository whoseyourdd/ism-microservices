package com.whoseyourdd.ism.student.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.whoseyourdd.ism.student.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * StudentDto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class StudentDto {
	private int studentId;
	private String studentName;
	private Date studentDob;
	private String studentEmail;
	private Gender studentGender;
	private String studentAddress;
	private ClassroomDto classroom;
}
