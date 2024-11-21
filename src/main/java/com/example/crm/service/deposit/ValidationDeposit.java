package com.example.crm.service.deposit;

import com.example.crm.dto.DepositDto;
import com.example.crm.entity.products.Deposit;

import java.util.List;
import java.util.function.Supplier;

public interface ValidationDeposit {
    void validateBalance(Double amount, Double balance);
    Deposit findDepositAndValidateStatus(DepositDto depositDto);
    List<Deposit> validateClientAndDeposits(Long clientId);
}
