package com.whoseyourdd.ism.classroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whoseyourdd.ism.classroom.model.Grade;

/**
 * GradeRepository
 */
public interface GradeRepository extends JpaRepository<Grade, Integer> {

	
}
