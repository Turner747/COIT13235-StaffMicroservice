package com.optimed.StaffMicroservice.controller;

import com.optimed.StaffMicroservice.mapper.ObjectMapper;
import com.optimed.StaffMicroservice.model.Role;
import com.optimed.StaffMicroservice.repository.RoleRepository;
import com.optimed.StaffMicroservice.response.RoleResponse;
import com.optimed.StaffMicroservice.service.RoleService;
import com.optimed.StaffMicroservice.service.StaffService;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RoleRepository role_repo;
    @GetMapping
    public ResponseEntity<Collection<RoleResponse>> getAllRoles() {
        List<Role> roles = role_repo.findAll();
        if(roles.isEmpty())
            return ResponseEntity.notFound().build();
        List<RoleResponse> role_response = ObjectMapper.mapAll(roles, RoleResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(role_response);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable("id") int id) {
        Optional<Role> optional = role_repo.findById(id);
        if(optional.isPresent()) {
            RoleResponse role_response = ObjectMapper.map(optional.get(), RoleResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(role_response);
        }
        return ResponseEntity.notFound().build();
    }
}
