package com.lapp.auth.controller;

import com.lapp.auth.model.RoleDTO;
import com.lapp.auth.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    public ResponseEntity<List<RoleDTO>> getRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoles());
    }

    @Override
    public ResponseEntity<RoleDTO> getRoleById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoleById(id));
    }

    @Override
    public ResponseEntity<RoleDTO> deleteRoleById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.deleteRoleById(id));
    }
}
