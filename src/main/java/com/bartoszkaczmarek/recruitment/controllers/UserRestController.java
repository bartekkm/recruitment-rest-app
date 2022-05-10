package com.bartoszkaczmarek.recruitment.controllers;

import com.bartoszkaczmarek.recruitment.domain.UserDataResponse;
import com.bartoszkaczmarek.recruitment.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public UserDataResponse getUserByLogin(@PathVariable("login") String login) {
        return userService.getUser(login);
    }
}
