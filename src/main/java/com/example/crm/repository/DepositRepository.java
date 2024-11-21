package com.example.crm.repository;

import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByEndDateBeforeAndStatus(LocalDateTime endDate, Status status);
    Optional<Deposit> findTopByOrderByIdDesc();
    List<Deposit> findAllByDepositHolderId(Long clientId);
    List<Deposit> findAllByDepositHolderIdOrderByEndDate(Long clientId);
}
