package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Role;
import com.vutheduyet.exercise03.service.RoleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/roles")
public class RoleController {

    private RoleService roleService;

    // Create Role REST API
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role savedRole = roleService.createRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    // Get Role by id REST API
    // http://localhost:8080/api/roles/{id}
    @GetMapping("{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") String roleId) {
        Role role = roleService.getRoleById(roleId);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    // Get All Roles REST API
    // http://localhost:8080/api/roles
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    // Update Role REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/roles/{id}
    public ResponseEntity<Role> updateRole(@PathVariable("id") String roleId,
                                            @RequestBody Role role) {
        role.setRoleId(roleId);
        Role updatedRole = roleService.updateRole(role);
        if (updatedRole != null) {
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Role REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") String roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>("Role successfully deleted!", HttpStatus.OK);
    }
}
