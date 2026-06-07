package com.lapp.auth.controller;

import com.lapp.auth.model.UserDTO;
import com.lapp.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserControllerInterface {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDTO> saveUser(UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(userDTO));
    }
}
