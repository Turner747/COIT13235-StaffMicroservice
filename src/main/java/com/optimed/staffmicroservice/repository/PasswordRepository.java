package com.optimed.staffmicroservice.repository;

import com.optimed.staffmicroservice.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
}
