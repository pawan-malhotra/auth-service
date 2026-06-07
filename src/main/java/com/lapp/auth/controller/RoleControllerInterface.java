package com.lapp.auth.controller;

import com.lapp.auth.model.RoleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
public interface RoleControllerInterface {

    @PostMapping("/roles")
    ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO roleDTO);

    @GetMapping("/roles")
    ResponseEntity<List<RoleDTO>> getRoles();

    @GetMapping("/roles/{id}")
    ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id);

    @DeleteMapping("/roles/{id}")
    ResponseEntity<RoleDTO> deleteRoleById(@PathVariable Long id);
}
