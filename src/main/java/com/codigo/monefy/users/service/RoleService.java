package com.codigo.monefy.users.service;

import com.codigo.monefy.exception.NotFoundException;
import com.codigo.monefy.users.dto.RoleDTO;
import com.codigo.monefy.users.dto.RoleRequestDTO;
import com.codigo.monefy.users.mapper.RoleMapper;
import com.codigo.monefy.users.persistence.entity.Role;
import com.codigo.monefy.users.persistence.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Integer id) {
        log.info("Finding role by ID: {}", id);
        return roleRepository.findById(id)
                .map(roleMapper::mapToDTO)
                .orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        log.info("Finding all roles");
        return roleRepository.findAll().stream()
                .map(roleMapper::mapToDTO)
                .toList();
    }

    public void saveRole(RoleRequestDTO roleRequestDTO) {
        log.info("Saving new role: {}", roleRequestDTO.getName());
        Role role = new Role();
        role.setName(roleRequestDTO.getName());
        role.setDescription(roleRequestDTO.getDescription());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        roleRepository.save(role);
    }

    public void updateRole(Integer id, RoleRequestDTO roleRequestDTO) {
        log.info("Updating role with ID: {}", id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));
        role.setName(roleRequestDTO.getName());
        role.setDescription(roleRequestDTO.getDescription());
        role.setUpdatedAt(LocalDateTime.now());
        roleRepository.save(role);
    }

    public void deleteRole(Integer id) {
        log.info("Deleting role with ID: {}", id);
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException("Role not found with ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}
