package com.example.crm.dto;

import com.example.crm.enums.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepositDto {
    private Long id;
    private Double amount;
    private Double interestRate;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Integer depositTermDays;
    private Long clientId;
    private LocalDateTime endDate;
}
