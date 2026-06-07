package com.lapp.auth.service;

import com.lapp.auth.exceptions.RoleNotFoundException;
import com.lapp.auth.exceptions.RoleServiceException;
import com.lapp.auth.model.Role;
import com.lapp.auth.model.RoleDTO;
import com.lapp.auth.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

//    field injection
//    @Autowired
//    private RoleRepository roleRepository;

    private RoleRepository roleRepository;

//    setter injection
//    @Autowired
//    public void setRoleRepository(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }

    // constructor injection
    RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDTO saveRole(RoleDTO roleDTO) {
        try {
            if (roleDTO == null || !StringUtils.hasText(roleDTO.getRoleName())) {
                throw new RoleNotFoundException("Please provide Role Name");
            }

            Role role = new Role();
            role.setRoleName(roleDTO.getRoleName());

            Role savedRole = roleRepository.save(role);

            RoleDTO roleDTO1 = new RoleDTO();
            roleDTO1.setId(savedRole.getId());
            roleDTO1.setRoleName(savedRole.getRoleName());

            return roleDTO1;
        } catch (RoleNotFoundException ex) {
            throw new RoleNotFoundException(ex.getMessage(), ex);
        } catch(Exception e) {
            throw new RoleServiceException(e.getMessage(), e);
        }
    }

    public List<RoleDTO> getRoles() {
        try {
            List<Role> roles = roleRepository.getAllRoles();
            return roles.stream().map(role -> new RoleDTO(role.getId(), role.getRoleName())).toList();
        } catch (Exception ex) {
            throw new RoleServiceException(ex.getMessage(), ex);
        }
    }

    public RoleDTO getRoleById(Long id) {
        try {
//            Optional<Role> roleOpt = roleRepository.findById(id);
//            if (roleOpt.isEmpty()) {
//                throw new RoleNotFoundException("Role not found!");
//            }
//
//            return new RoleDTO(roleOpt.get().getId(), roleOpt.get().getRoleName());

            Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
            return new RoleDTO(role.getId(), role.getRoleName());
        } catch (RoleNotFoundException ex) {
            System.out.println(ex);
            throw new RoleNotFoundException(ex.getMessage(), ex);
        } catch (Exception e) {
            System.out.println(e);
            throw new RoleServiceException(e.getMessage(), e);
        }
    }

    public RoleDTO deleteRoleById(Long id) {
        try {
            RoleDTO roleDTO = getRoleById(id);
            roleRepository.deleteById(id);
            return roleDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RoleServiceException(ex.getMessage(), ex);
        }
    }
}
