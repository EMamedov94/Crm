package com.example.crm.service.deposit;

import com.example.crm.dto.DepositDto;
import com.example.crm.entity.products.Deposit;

public interface DepositService {
    Deposit openNewDeposit(DepositDto deposit);
    Deposit closeDeposit(DepositDto depositDto);
    Double calculateClosureAmount(DepositDto depositDto);
}
