package com.whoseyourdd.ism.classroom.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.whoseyourdd.ism.classroom.dto.TeacherDto;

/**
 * TeacherService
 */
@FeignClient(name = "teacher-ms", url = "${application.config.ms.teacher}")
public interface TeacherService {

    @GetMapping("/classroom/{id}")
    TeacherDto findTeacherByClassroomId(@PathVariable("id") Integer id);
}
