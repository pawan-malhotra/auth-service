package com.lapp.auth.service;

import com.lapp.auth.exceptions.UserServiceException;
import com.lapp.auth.model.Role;
import com.lapp.auth.model.RoleDTO;
import com.lapp.auth.model.User;
import com.lapp.auth.model.UserDTO;
import com.lapp.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        try {
            if (userDTO == null) {
                throw new UserServiceException("User Data Not Found");
            }

            validateUserName(userDTO.getUsername());
            validateEmail(userDTO.getEmail());
            validatePassword(userDTO.getPassword());

            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setAccountNonLocked(true);
            user.setEnabled(true);
            Set<Role> roleSet = new HashSet<>();
            userDTO.getRoles().forEach(role -> roleSet.add(new Role(role.getId(), role.getRoleName())));
            user.setRoles(roleSet);

            User savUser = userRepository.save(user);

            Set<RoleDTO> roleDTOSet = new HashSet<>();
            savUser.getRoles().forEach(role -> roleDTOSet.add(new RoleDTO(role.getId(), role.getRoleName())));

            return UserDTO.builder()
                    .id(savUser.getId())
                    .username(savUser.getUsername())
                    .email(savUser.getEmail())
                    .accountNonLocked(savUser.isAccountNonLocked())
                    .enabled(savUser.isEnabled())
                    .roles(roleDTOSet)
                    .build();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UserServiceException(ex.getMessage(), ex);
        }
    }

    private void validateUserName(String username) throws UserServiceException {
        if (!StringUtils.hasText(username)) {
            throw new UserServiceException("Please provide username");
        }
    }

    private void validateEmail(String email) throws UserServiceException {
        if (!StringUtils.hasText(email)) {
            throw new UserServiceException("Please provide valid email");
        }
    }

    private void validatePassword(String password) throws UserServiceException {
        if (!StringUtils.hasText(password)) {
            throw new UserServiceException("Please provide password");
        }
    }
}
