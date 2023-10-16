package com.optimed.staffmicroservice.controller;

import com.optimed.staffmicroservice.mapper.ObjectMapper;
import com.optimed.staffmicroservice.model.Password;
import com.optimed.staffmicroservice.model.Staff;
import com.optimed.staffmicroservice.repository.PasswordRepository;
import com.optimed.staffmicroservice.repository.StaffRepository;
import com.optimed.staffmicroservice.response.PasswordResponse;
import com.optimed.staffmicroservice.response.ShiftResponse;
import com.optimed.staffmicroservice.response.StaffResponse;
import jakarta.ws.rs.Path;
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
    private PasswordRepository passwordRepo;
    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<StaffResponse> saveStaff(@RequestBody StaffResponse staffResponse) {
        System.out.println(staffResponse);
        Staff newStaff = staffRepo.save(ObjectMapper.map(staffResponse, Staff.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(ObjectMapper.map(newStaff, StaffResponse.class));
//        return null;
    }
    @GetMapping
    public ResponseEntity<Collection<StaffResponse>> getAllStaffs() {
        List<Staff> staffs = staffRepo.findAll();
        if(staffs.isEmpty())
            return ResponseEntity.notFound().build();
        List<StaffResponse> staffResponses = ObjectMapper.mapAll(staffs, StaffResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(staffResponses);
    }
    @GetMapping("/doctors")
    public ResponseEntity<Collection<StaffResponse>> getAllDoctors() {
        List<Staff> staffs = staffRepo.findAllDoctors();
        if(staffs.isEmpty())
            return ResponseEntity.notFound().build();
        List<StaffResponse> staffResponses = ObjectMapper.mapAll(staffs, StaffResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(staffResponses);
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
    @GetMapping("email/{email}")
    public ResponseEntity<StaffResponse> getStaffByEmail(@PathVariable("email") String email) {
        Optional<Staff> optional = staffRepo.findByEmail(email);
        if(optional.isPresent()) {
            StaffResponse staffResponse = ObjectMapper.map(optional, StaffResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(staffResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<StaffResponse> deleteStaffById(@PathVariable("id") long id) {
        Optional<Staff> optional = staffRepo.findById(id);
        if (optional.isPresent()) {
            staffRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("password/{id}")
    public ResponseEntity<PasswordResponse> getPasswordById(@PathVariable("id") long id) {
        Optional<Password> optional = passwordRepo.findById(id);
        if(optional.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(ObjectMapper.map(optional.get(), PasswordResponse.class));
    }
}
