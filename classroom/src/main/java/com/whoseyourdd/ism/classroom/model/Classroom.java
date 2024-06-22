package com.whoseyourdd.ism.classroom.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classroom
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classrooms", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"grade_id", "classroom_name"})
})
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int classroomId;

	@Column(name = "classroom_name")
  private String classroomName;

	@Column(name = "grade_id")
	private int gradeId;

	@ManyToOne
	@JoinColumn(name = "grade_id", insertable = false, updatable = false)
	private Grade grade;
}
