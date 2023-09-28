package com.optimed.StaffMicroservice.repository;

import com.optimed.StaffMicroservice.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
//    public List<Staff> findByFirstNameContaining(String firstName);
//    public List<Staff> findByLastNameContaining(String lastName);

//    @Query("SELECT s FROM Staff s WHERE s.role.name = :query")
//    public List<Staff> getAllStaffByRole(String query);
}
