package com.optimed.staffmicroservice.repository;

import com.optimed.staffmicroservice.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    public Optional<Staff> findByEmail(String email);
    @Query("SELECT s FROM Staff s WHERE s.prescriberNumber IS NOT NULL OR s.prescriberNumber <> ''")
    public List<Staff> findAllDoctors();
}
