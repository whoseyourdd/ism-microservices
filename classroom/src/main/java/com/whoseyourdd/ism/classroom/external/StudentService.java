package com.whoseyourdd.ism.classroom.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.whoseyourdd.ism.classroom.dto.StudentDto;
/**
 * StudentService
 */
@FeignClient(name = "student-ms", url = "${application.config.ms.student}")
public interface StudentService {

    @GetMapping("/classrooms/{id}")
    List<StudentDto> findAllStudentsByClassroomId(@PathVariable("id") Integer id);
}
