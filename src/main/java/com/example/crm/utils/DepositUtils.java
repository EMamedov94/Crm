package com.example.crm.utils;

import com.example.crm.entity.products.Deposit;
import com.example.crm.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepositUtils {
    private final DepositRepository depositRepository;

    public String generateDepositNumber() {
        String lastDepositNumber = depositRepository.findTopByOrderByIdDesc()
                .map(Deposit::getDepositNumber)
                .orElse("P1");

        int lastNumber = Integer.parseInt(lastDepositNumber.substring(1));

        return  "P" + (lastNumber + 1);
    }
}
