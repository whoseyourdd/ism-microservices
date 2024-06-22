package com.whoseyourdd.ism.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whoseyourdd.ism.student.model.Student;

/**
 * StudentRepository
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByClassroomId(int classroomId);
}
