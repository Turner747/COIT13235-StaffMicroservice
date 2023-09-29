package com.optimed.StaffMicroservice.controller;

import com.optimed.StaffMicroservice.mapper.ObjectMapper;
import com.optimed.StaffMicroservice.model.Staff;
import com.optimed.StaffMicroservice.repository.StaffRepository;
import com.optimed.StaffMicroservice.response.StaffResponse;
import com.optimed.StaffMicroservice.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/restapi/staffs")
public class StaffController {
    private StaffRepository staff_repo;
    @PostMapping
    public ResponseEntity<Staff> saveProduct(@RequestBody Staff staff) {
        return new ResponseEntity<Staff>(staff_repo.save(staff), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Collection<StaffResponse>> getAllStaffs() {
        List<Staff> staffs = staff_repo.findAll();
        if(staffs.isEmpty())
            return ResponseEntity.notFound().build();
        List<StaffResponse> staff_response = ObjectMapper.mapAll(staffs, StaffResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(staff_response);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        Optional<Staff> optional = staff_repo.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Staff new_staff = optional.get();
        new_staff = ObjectMapper.map(staff, Staff.class);

        return ResponseEntity.ok(staff_repo.save(new_staff));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<StaffResponse> getStaffById(@PathVariable("id") long id) {
        Optional<Staff> optional = staff_repo.findById(id);
        if(optional.isPresent()) {
            StaffResponse staff_response = ObjectMapper.map(optional, StaffResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(staff_response);
        }
        return ResponseEntity.notFound().build();
    }
}
