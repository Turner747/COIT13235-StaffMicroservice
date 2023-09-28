package com.optimed.StaffMicroservice.service;

import com.optimed.StaffMicroservice.model.Privilege;
import com.optimed.StaffMicroservice.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
    @Autowired
    private PrivilegeRepository repo;

    public PrivilegeService(PrivilegeRepository repo) {
        super();
        this.repo = repo;
    }

    public Privilege findByName(String name) {
        return repo.findByName(name);
    }

    public void save(Privilege privilege) {
        repo.save(privilege);
    }
}
