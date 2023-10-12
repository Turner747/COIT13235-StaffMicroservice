package com.optimed.staffmicroservice.repository;

import com.optimed.staffmicroservice.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query("SELECT s FROM Shift s WHERE s.staff.id = :id")
    public List<Shift> findShiftByStaffId(long id);
}
