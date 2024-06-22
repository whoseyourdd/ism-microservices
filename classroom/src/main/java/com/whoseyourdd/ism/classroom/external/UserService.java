package com.whoseyourdd.ism.classroom.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.whoseyourdd.ism.classroom.dto.UserDto;
/**
 * StudentService
 */
@FeignClient(name = "user-ms", url = "${application.config.ms.user}")
public interface UserService {

    @GetMapping("/{username}")
    UserDto GetUserCreds(@PathVariable("username") String username);
}

