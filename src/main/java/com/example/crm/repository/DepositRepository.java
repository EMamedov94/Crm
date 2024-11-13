package com.example.crm.repository;

import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByEndDateBeforeAndStatus(LocalDateTime endDate, Status status);
    Optional<Deposit> findTopByOrderByIdDesc();
}
