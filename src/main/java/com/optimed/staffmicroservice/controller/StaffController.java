package com.optimed.staffmicroservice.controller;

import com.optimed.staffmicroservice.mapper.ObjectMapper;
import com.optimed.staffmicroservice.model.Staff;
import com.optimed.staffmicroservice.repository.StaffRepository;
import com.optimed.staffmicroservice.response.StaffResponse;
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
    private StaffRepository staffRepo;
    @PostMapping
    public ResponseEntity<StaffResponse> saveProduct(@RequestBody Staff staff) {
        Staff newStaff = staffRepo.save(staff);
        return ResponseEntity.status(HttpStatus.CREATED).body(ObjectMapper.map(newStaff, StaffResponse.class));
    }
    @GetMapping
    public ResponseEntity<Collection<StaffResponse>> getAllStaffs() {
        List<Staff> staffs = staffRepo.findAll();
        if(staffs.isEmpty())
            return ResponseEntity.notFound().build();
        List<StaffResponse> staffResponses = ObjectMapper.mapAll(staffs, StaffResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(staffResponses);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<StaffResponse> updateStaff(@PathVariable Long id, @RequestBody Staff updatedStaff) {
        Optional<Staff> optional = staffRepo.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Staff existingStaff = optional.get();
        existingStaff = ObjectMapper.map(updatedStaff, Staff.class);

        StaffResponse staffResponse = ObjectMapper.map(staffRepo.save(existingStaff), StaffResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(staffResponse);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<StaffResponse> getStaffById(@PathVariable("id") long id) {
        Optional<Staff> optional = staffRepo.findById(id);
        if(optional.isPresent()) {
            StaffResponse staffResponse = ObjectMapper.map(optional, StaffResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(staffResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
