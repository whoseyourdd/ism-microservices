package com.whoseyourdd.ism.student.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Student
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int studentId;

	@Column(name = "student_name")
  private String studentName;

	@Column(name = "student_dob")
	private Date studentDob;

	@Column(name = "student_email")
  private String studentEmail;

	@Column(name = "student_gender")
	private Gender studentGender;
	
	@Column(name = "student_address")
	private String studentAddress;

	@Column(name = "classroom_id")
	private int classroomId;
}
