package com.codigo.monefy.users.mapper;

import com.codigo.monefy.users.dto.RoleDTO;
import com.codigo.monefy.users.dto.UserDTO;
import com.codigo.monefy.users.persistence.entity.Role;
import com.codigo.monefy.users.persistence.entity.User;
import com.codigo.monefy.users.persistence.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO mapToDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPasswordHash());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setProfileImageUrl(user.getProfileImageUrl());
        userDTO.setActive(user.isActive());
        userDTO.setVerified(user.isVerified());

        if (user.getRoles() != null) {
            Set<RoleDTO> roleDTOs = user.getRoles().stream()
                    .map(role -> {
                        RoleDTO roleDTO = new RoleDTO();
                        roleDTO.setId(role.getId());
                        roleDTO.setName(role.getName());
                        roleDTO.setDescription(role.getDescription());
                        return roleDTO;
                    })
                    .collect(Collectors.toSet());
            userDTO.setRoles(roleDTOs);
        }

        return userDTO;
    }
}
