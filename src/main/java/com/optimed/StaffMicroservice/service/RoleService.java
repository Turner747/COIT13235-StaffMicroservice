package com.optimed.StaffMicroservice.service;

import com.optimed.StaffMicroservice.model.Role;
import com.optimed.StaffMicroservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repo;

    public RoleService(RoleRepository repo) {
        super();
        this.repo = repo;
    }

    public Role findByName(String name) {
        return repo.findByName(name);
    }

    public Role findById(int roleId) {
        Optional<Role> optional = repo.findById(roleId);
        return optional.orElse(null);
    }

    public void save(Role role) {
        repo.save(role);
    }
}
