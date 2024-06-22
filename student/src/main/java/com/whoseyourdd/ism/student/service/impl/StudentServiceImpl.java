package com.whoseyourdd.ism.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whoseyourdd.ism.student.dto.ClassroomDto;
import com.whoseyourdd.ism.student.dto.RequestEnrollStudentToClassroomDto;
import com.whoseyourdd.ism.student.dto.StudentDto;
import com.whoseyourdd.ism.student.external.ClassroomService;
import com.whoseyourdd.ism.student.model.Student;
import com.whoseyourdd.ism.student.repository.StudentRepository;
import com.whoseyourdd.ism.student.service.StudentService;
import com.whoseyourdd.ism.student.utils.exception.ErrInternalException;
import com.whoseyourdd.ism.student.utils.exception.ErrNotFoundException;
import com.whoseyourdd.ism.student.utils.mapper.StudentMapper;

/**
 * StudentServiceImpl
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<StudentDto> findAllStudents() throws Exception {
		try {
			List<Student> students = studentRepository.findAll();
			List<StudentDto> results = new ArrayList<>();
			for(Student s : students) {
				StudentDto student = studentMapper.ToStudentDto(s, null);
				results.add(student);
			}
			return results;
		} catch (Exception e) {
			throw new ErrInternalException("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public StudentDto findStudentById(int id) throws Exception {
		try{
			Student student = studentRepository.findById(id).orElse(null);
			if(student == null) {
				return null;
			}
			ClassroomDto classroom = classroomService.findClassroomById(student.getClassroomId());
		  StudentDto result = studentMapper.ToStudentDto(student, classroom);
			return result;
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public StudentDto addStudent(StudentDto student) throws Exception {
		try{
			Student studentModel = studentMapper.ToStudentModel(student);
			studentModel = studentRepository.save(studentModel);
			return studentMapper.ToStudentDto(studentModel, null);
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public StudentDto updateStudent(StudentDto student) throws Exception {
		try{
			Student s = studentRepository.findById(student.getStudentId()).orElse(null);
			if(s == null){
				throw new ErrNotFoundException("Student with id " + student.getStudentId() + " does not exist");
			}
			ClassroomDto classroom = classroomService.findClassroomById(student.getClassroom().getClassroomId());
			if(classroom == null){
				throw new ErrNotFoundException("Classroom with id " + student.getClassroom().getClassroomId() + " does not exist");
			}
			s = studentRepository.save(studentMapper.ToStudentModel(student));
			return studentMapper.ToStudentDto(s, classroom);
		}catch(Exception e){
      throw new ErrInternalException("Internal server error: " + e.getMessage());
    }
	}

	@Override
	public void deleteStudent(int id) throws Exception {
		try{
			Student s = studentRepository.findById(id).orElse(null);
			if(s == null){
				throw new ErrNotFoundException("Student with id " + id + " does not exist");
			}
			studentRepository.delete(s);
		}catch(Exception e){
      throw new ErrInternalException("Internal server error: " + e.getMessage());
    }
	}

	@Override
	public List<StudentDto> findStudentsByClassroomId(int classroomId) throws Exception {
		try{
			List<Student> students = studentRepository.findByClassroomId(classroomId);
			List<StudentDto> results = new ArrayList<>();
			for(Student s : students) {
				StudentDto student = studentMapper.ToStudentDto(s, null);
				results.add(student);
			}
			return results;
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public StudentDto enrollStudentToClassroom(RequestEnrollStudentToClassroomDto enrollStudetDto) throws Exception {
		try{
			Student s = studentRepository.findById(enrollStudetDto.getStudentId()).orElse(null);
			if(s == null){
				throw new ErrNotFoundException("Student with id " + enrollStudetDto.getStudentId() + " does not exist");
			}
			ClassroomDto c = classroomService.findClassroomById(enrollStudetDto.getClassroomId());
			if(c == null){
				throw new ErrNotFoundException("Classroom with id " + enrollStudetDto.getClassroomId() + " does not exist");
			}
			s.setClassroomId(enrollStudetDto.getClassroomId());
			s = studentRepository.save(s);
			return studentMapper.ToStudentDto(s, c);
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: " + e.getMessage());
		}
	}
	
}
