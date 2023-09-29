package com.optimed.StaffMicroservice.controller;

import com.optimed.StaffMicroservice.mapper.ObjectMapper;
import com.optimed.StaffMicroservice.model.Shift;
import com.optimed.StaffMicroservice.repository.ShiftRepository;
import com.optimed.StaffMicroservice.response.ShiftResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/restapi/shifts")
public class ShiftController {
    public ShiftRepository shift_repo;
    @PostMapping
    public ResponseEntity<Shift> saveProduct(@RequestBody Shift shift) {
        return new ResponseEntity<Shift>(shift_repo.save(shift), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Collection<ShiftResponse>> getAllShifts() {
        List<Shift> shifts = shift_repo.findAll();
        if(shifts.isEmpty())
            return ResponseEntity.notFound().build();
        List<ShiftResponse> shift_response = ObjectMapper.mapAll(shifts, ShiftResponse.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(shift_response);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Shift> updateShift(@PathVariable Long id, @RequestBody Shift shift) {
        Optional<Shift> optional = shift_repo.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Shift new_shift = optional.get();
        new_shift = ObjectMapper.map(shift, Shift.class);
        return ResponseEntity.ok(shift_repo.save(new_shift));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ShiftResponse> getShiftById(@PathVariable("id") long id) {
        Optional<Shift> optional = shift_repo.findById(id);
        if(optional.isPresent()) {
            ShiftResponse shift_response = ObjectMapper.map(optional, ShiftResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(shift_response);
        }
        return ResponseEntity.notFound().build();
    }
}
