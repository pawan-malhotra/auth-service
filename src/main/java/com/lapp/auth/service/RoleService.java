package com.lapp.auth.service;

import com.lapp.auth.exceptions.RoleNotFoundException;
import com.lapp.auth.exceptions.RoleServiceException;
import com.lapp.auth.model.Role;
import com.lapp.auth.model.RoleDTO;
import com.lapp.auth.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
}
