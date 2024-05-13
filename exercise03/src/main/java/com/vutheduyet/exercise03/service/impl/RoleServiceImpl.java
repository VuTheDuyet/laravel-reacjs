package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Role;
import com.vutheduyet.exercise03.repository.RoleRepository;
import com.vutheduyet.exercise03.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(String roleId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        return optionalRole.orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Role role) {
        Optional<Role> optionalExistingRole = roleRepository.findById(role.getRoleId());
        if (optionalExistingRole.isPresent()) {
            Role existingRole = optionalExistingRole.get();
            existingRole.setRoleName(role.getRoleName());
            existingRole.setPrivileges(role.getPrivileges());

            Role updatedRole = roleRepository.save(existingRole);
            return updatedRole;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy Role tồn tại
    }

    @Override
    public void deleteRole(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
