package com.goit.finalproject.service;

import com.goit.finalproject.entity.Role;
import com.goit.finalproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
