package com.lapp.auth.controller;

import com.lapp.auth.model.RoleDTO;
import com.lapp.auth.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController implements RoleControllerInterface {

    private RoleService roleService;

    RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<RoleDTO> saveRole(RoleDTO roleDTO) {
        // ResponseEntity.status(<status-code>).body(<body>);
        RoleDTO roleDTO1 = roleService.saveRole(roleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleDTO1);
    }
}
