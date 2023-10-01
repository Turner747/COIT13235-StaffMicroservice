package com.optimed.staffmicroservice.repository;

import com.optimed.staffmicroservice.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
//    public List<Staff> findByFirstNameContaining(String firstName);
//    public List<Staff> findByLastNameContaining(String lastName);

//    @Query("SELECT s FROM Staff s WHERE s.role.name = :query")
//    public List<Staff> getAllStaffByRole(String query);
}
