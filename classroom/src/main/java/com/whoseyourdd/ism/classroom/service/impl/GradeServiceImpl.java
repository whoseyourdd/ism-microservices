package com.whoseyourdd.ism.classroom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whoseyourdd.ism.classroom.dto.GradeDto;
import com.whoseyourdd.ism.classroom.model.Grade;
import com.whoseyourdd.ism.classroom.repository.GradeRepository;
import com.whoseyourdd.ism.classroom.service.GradeService;
import com.whoseyourdd.ism.classroom.utils.exception.ErrInternalException;
import com.whoseyourdd.ism.classroom.utils.exception.ErrNotFoundException;
import com.whoseyourdd.ism.classroom.utils.mapper.GradeMapper;

/**
 * GradeServiceImpl
 */
@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private GradeMapper gradeMapper;

	@Override
	public List<GradeDto> findAllGrades() throws Exception {
		try{
			List<Grade> grades = gradeRepository.findAll();
			List<GradeDto> result = new ArrayList<>();
			for (Grade g : grades) {
				GradeDto grade = gradeMapper.ToGradeDto(g);
				result.add(grade);
			}
			return result;
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: "+e.getMessage());
		}
	}

	@Override
	public GradeDto findGradeById(int id) throws Exception {
		try{
			Grade grade = gradeRepository.findById(id).orElse(null);
			if(grade == null) {
				throw new ErrNotFoundException("Grade with id: "+id+" does not exist");
			}
			return gradeMapper.ToGradeDto(grade);
		} catch(ErrNotFoundException e){
			throw e;
		} catch(Exception e){
			throw new ErrInternalException("Internal server error: "+e.getMessage());
		}
	}

	@Override
	public GradeDto createGrade(GradeDto grade) throws Exception {
		try{
			Grade g = gradeMapper.ToGradeModel(grade);
			Grade g1 = gradeRepository.save(g);
			return gradeMapper.ToGradeDto(g1);
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: "+e.getMessage());
		}
	}

	@Override
	public GradeDto updateGrade(GradeDto grade) throws Exception {
		try{
			Grade g = gradeRepository.findById(grade.getGradeId()).orElse(null);
			if(g == null) {
				throw new ErrNotFoundException("Grade with id: "+grade.getGradeId()+" does not exist");
			}
			g.setGradeLevel(grade.getGradeLevel());
			Grade g1 = gradeRepository.save(g);
			return gradeMapper.ToGradeDto(g1);
		} catch(ErrNotFoundException e){
			throw e;
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: "+e.getMessage());
		}
	}

	@Override
	public void deleteGrade(int id) throws Exception {
		try{
			Grade g = gradeRepository.findById(id).orElse(null);
			if(g == null) {
				throw new ErrNotFoundException("Grade with id: "+id+" does not exist");
			}
			gradeRepository.delete(g);
		} catch(ErrNotFoundException e){
			throw e;
		}catch(Exception e){
			throw new ErrInternalException("Internal server error: "+e.getMessage());
		}
	}

	
}
