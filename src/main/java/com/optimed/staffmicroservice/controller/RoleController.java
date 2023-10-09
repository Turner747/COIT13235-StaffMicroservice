package com.optimed.staffmicroservice.controller;

import com.optimed.staffmicroservice.mapper.ObjectMapper;
import com.optimed.staffmicroservice.model.Role;
import com.optimed.staffmicroservice.repository.RoleRepository;
import com.optimed.staffmicroservice.response.RoleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/restapi/roles")
public class RoleController {
    private RoleRepository roleRepo;
    @GetMapping
    public ResponseEntity<Collection<RoleResponse>> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        if(roles.isEmpty())
            return ResponseEntity.notFound().build();
        List<RoleResponse> roleResponses = ObjectMapper.mapAll(roles, RoleResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(roleResponses);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable("id") int id) {
        Optional<Role> optional = roleRepo.findById(id);
        if(optional.isPresent()) {
            RoleResponse roleResponse = ObjectMapper.map(optional.get(), RoleResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(roleResponse);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<RoleResponse> getRoleByName(@PathVariable("name") String name) {
        Role role = roleRepo.findByName(name);
        if(role!=null) {
            RoleResponse roleResponse = ObjectMapper.map(role, RoleResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(roleResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
