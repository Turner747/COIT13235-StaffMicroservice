package com.optimed.StaffMicroservice.service;

import com.optimed.StaffMicroservice.mapper.ObjectMapper;
import com.optimed.StaffMicroservice.model.Staff;
import com.optimed.StaffMicroservice.repository.StaffRepository;
import com.optimed.StaffMicroservice.response.StaffResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//@AllArgsConstructor
public class StaffService {
////    @Autowired
//    private StaffRepository repo;
////    @Autowired
////    private ModelMapper mapper;
////    public StaffService(StaffRepository staffRepo) {
////        super();
////        this.repo = staffRepo;
////    }
//    public List<StaffResponse> getAllStaffs() {
//        List<Staff> staffs = repo.findAll();
//        return ObjectMapper.mapAll(staffs, StaffResponse.class);
//    }
//    public StaffResponse getStaffById(long id) {
//        Optional<Staff> optional = repo.findById(id);
//        if(optional.isPresent())
//            return ObjectMapper.map(optional, StaffResponse.class);
//        return null;
//    }
//    public Staff saveStaff(Staff staff) {
//        return repo.save(staff);
//    }
}
