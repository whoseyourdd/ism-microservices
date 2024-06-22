package com.whoseyourdd.ism.classroom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whoseyourdd.ism.classroom.dto.ClassroomDto;
import com.whoseyourdd.ism.classroom.dto.StudentDto;
import com.whoseyourdd.ism.classroom.external.StudentService;
import com.whoseyourdd.ism.classroom.model.Classroom;
import com.whoseyourdd.ism.classroom.model.Grade;
import com.whoseyourdd.ism.classroom.repository.ClassroomRepository;
import com.whoseyourdd.ism.classroom.repository.GradeRepository;
import com.whoseyourdd.ism.classroom.service.ClassroomService;
import com.whoseyourdd.ism.classroom.utils.exception.ErrNotFoundException;
import com.whoseyourdd.ism.classroom.utils.mapper.ClassroomMapper;

/**
 * ClassroomServiceImpl
 */
@Service
public class ClassroomServiceImpl implements ClassroomService{

	@Autowired
	private ClassroomRepository classroomRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ClassroomMapper classroomMapper;

	@Override
	public List<ClassroomDto> findAllClassrooms() throws Exception {
		try{
			List<Classroom> classrooms = classroomRepository.findAll();
			List<ClassroomDto> result = new ArrayList<>();
			for(Classroom c : classrooms){
				ClassroomDto classroom = classroomMapper.ToClassroomDto(c, c.getGrade(), null);
				result.add(classroom);
			}
			return result;
		}catch(Exception e){
			throw new Exception("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public ClassroomDto findClassroomById(int id) throws Exception {
		try{
			Classroom c = classroomRepository.findById(id).orElse(null);
			if(c == null){
				throw new ErrNotFoundException("Classroom with id " + id + " does not exist");
			}
			List<StudentDto> students = studentService.findAllStudentsByClassroomId(c.getClassroomId());
			ClassroomDto classroom = classroomMapper.ToClassroomDto(c, c.getGrade(), students);
			return classroom;
		}catch(ErrNotFoundException e){
			throw e;
		}catch(Exception e){
			throw new Exception("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public List<ClassroomDto> findClassroomByGrade(int gradeId) throws Exception {
		try{
			List<Classroom> classrooms = classroomRepository.findByGradeId(gradeId);
			List<ClassroomDto> result = new ArrayList<>();
			for(Classroom c : classrooms){
				ClassroomDto classroom = classroomMapper.ToClassroomDto(c, c.getGrade(), null);
				result.add(classroom);
			}
			return result;
		}catch(Exception e){
			throw new Exception("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public ClassroomDto addClassroom(ClassroomDto classroomDto) throws Exception {
		try{
			Classroom c = classroomMapper.ToClassroomModel(classroomDto);
			Grade g = gradeRepository.findById(c.getGradeId()).orElse(null);
			if (g == null){
				throw new ErrNotFoundException("Grade with id " + c.getGradeId() + " does not exist");
			}
			Classroom newClass = classroomRepository.save(c);
			return classroomMapper.ToClassroomDto(newClass, g, null);
		}catch(ErrNotFoundException e){
			throw e;
		}catch(Exception e){
			throw new Exception("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public void deleteClassroom(int id) throws Exception {
		try{
			Classroom c = classroomRepository.findById(id).orElse(null);
			if(c == null){
				throw new ErrNotFoundException("Classroom with id " + id + " does not exist");
			}
		classroomRepository.delete(c);
		}catch(ErrNotFoundException e){
			throw e;
		}catch(Exception e){
			throw new Exception("Internal server error: " + e.getMessage());
		}
	}

	@Override
	public ClassroomDto updateClassroom(ClassroomDto classroomDto) throws Exception {
		try{
			Classroom c = classroomRepository.findById(classroomDto.getClassroomId()).orElse(null);
      if(c == null){
        throw new ErrNotFoundException("Classroom with id " + classroomDto.getClassroomId() + " does not exist");
      }
      Classroom newClass = classroomRepository.save(classroomMapper.ToClassroomModel(classroomDto));
      return classroomMapper.ToClassroomDto(newClass, c.getGrade(), null);
		}catch(ErrNotFoundException e){
			throw e;
		}catch(Exception e){
			throw new Exception("Internal server error: " + e.getMessage());
		}
	}

	
}
