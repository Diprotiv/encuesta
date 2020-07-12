package com.diprotiv.encuesta.controller;

import com.diprotiv.encuesta.model.request.SaveUserRequest;
import com.diprotiv.encuesta.model.response.GetUserResponse;
import com.diprotiv.encuesta.model.response.SaveUserResponse;
import com.diprotiv.encuesta.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public SaveUserResponse saveUser(@RequestBody final SaveUserRequest saveUserRequest) {
        return userService.saveUser(saveUserRequest);
    }

    @GetMapping("/{userId}")
    public GetUserResponse getUser(@PathVariable("userId") final String userId) {
        return userService.getUser(userId);
    }
}
