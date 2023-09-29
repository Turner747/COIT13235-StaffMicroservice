package com.optimed.StaffMicroservice.service;

import com.optimed.StaffMicroservice.mapper.ObjectMapper;
import com.optimed.StaffMicroservice.model.Role;
import com.optimed.StaffMicroservice.repository.RoleRepository;
import com.optimed.StaffMicroservice.response.RoleResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//@AllArgsConstructor
public class RoleService {
//    private RoleRepository repo;
//    public List<RoleResponse> getAllRoles() {
//        List<Role> roles = repo.findAll();
//        return ObjectMapper.mapAll(roles, RoleResponse.class);
//    }
//
//    public Role getRoleByName(String name) {
//        return repo.findByName(name);
//    }
//
//    public RoleResponse getRoleById(int id) {
//        Optional<Role> optional = repo.findById(id);
//        if(optional.isPresent())
//            return ObjectMapper.map(optional.get(), RoleResponse.class);
//        return null;
//    }
//
//    public void saveRole(Role role) {
//        repo.save(role);
//    }
}
