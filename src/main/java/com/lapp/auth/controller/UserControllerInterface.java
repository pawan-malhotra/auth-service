package com.lapp.auth.controller;

import com.lapp.auth.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public interface UserControllerInterface {

    @PostMapping("/users")
    ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO);
}
