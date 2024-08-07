package com.example.crm.service.impl.deposit;

import com.example.crm.exception.ValidationException;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.deposit.ValidationDeposit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationDepositImpl implements ValidationDeposit {

    private final PersonRepository personRepository;

    @Override
    public void validateBalance(Long clientId, Double balance) {
        Double clientBalance = personRepository.findBalanceByClientId(clientId);

        List<String> errors = new ArrayList<>();

        if (balance == null || balance >= clientBalance) {
            errors.add("Недостаточно средств");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
