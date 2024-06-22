package com.whoseyourdd.ism.student.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.whoseyourdd.ism.student.dto.ClassroomDto;

/**
 * ClassroomService
 */
@FeignClient(name = "classroom-ms", url = "${application.config.ms.classroom}")
public interface ClassroomService {

	@GetMapping("/{id}")
	ClassroomDto findClassroomById(@PathVariable("id") int id);

}	
