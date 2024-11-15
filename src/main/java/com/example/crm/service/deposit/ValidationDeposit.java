package com.example.crm.service.deposit;

import com.example.crm.dto.DepositDto;
import com.example.crm.entity.products.Deposit;

public interface ValidationDeposit {
    void validateBalance(Double amount, Double balance);
    Deposit findDepositAndValidateStatus(DepositDto depositDto);
}
