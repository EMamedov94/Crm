package com.example.crm.service;

import com.example.crm.entity.products.Deposit;
import org.springframework.security.core.userdetails.UserDetails;

public interface DepositService {
    Deposit openNewDeposit(Deposit deposit);
    Deposit closeDeposit(Deposit deposit);
}
