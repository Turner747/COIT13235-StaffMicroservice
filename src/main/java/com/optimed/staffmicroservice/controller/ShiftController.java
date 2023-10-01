package com.optimed.staffmicroservice.controller;

import com.optimed.staffmicroservice.mapper.ObjectMapper;
import com.optimed.staffmicroservice.model.Shift;
import com.optimed.staffmicroservice.repository.ShiftRepository;
import com.optimed.staffmicroservice.response.ShiftResponse;
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
    public ShiftRepository shiftRepo;
    @PostMapping
    public ResponseEntity<Shift> saveProduct(@RequestBody Shift shift) {
        return new ResponseEntity<Shift>(shiftRepo.save(shift), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Collection<ShiftResponse>> getAllShifts() {
        List<Shift> shifts = shiftRepo.findAll();
        if(shifts.isEmpty())
            return ResponseEntity.notFound().build();
        List<ShiftResponse> shiftResponses = ObjectMapper.mapAll(shifts, ShiftResponse.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(shiftResponses);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Shift> updateShift(@PathVariable Long id, @RequestBody Shift shift) {
        Optional<Shift> optional = shiftRepo.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Shift newShift = optional.get();
        newShift = ObjectMapper.map(shift, Shift.class);
        return ResponseEntity.ok(shiftRepo.save(newShift));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ShiftResponse> getShiftById(@PathVariable("id") long id) {
        Optional<Shift> optional = shiftRepo.findById(id);
        if(optional.isPresent()) {
            ShiftResponse shiftResponse = ObjectMapper.map(optional, ShiftResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(shiftResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
