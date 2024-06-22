package com.whoseyourdd.ism.classroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whoseyourdd.ism.classroom.model.Classroom;

/**
 * ClassroomRepository
 */
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

	List<Classroom> findByGradeId(int gradeId);
	
}
