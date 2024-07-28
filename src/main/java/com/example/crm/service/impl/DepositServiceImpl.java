<<<<<<< HEAD
package com.example.crm.service.impl;

import com.example.crm.entity.Person;
import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Status;
import com.example.crm.repository.DepositRepository;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {
    private final DepositRepository depositRepository;
    private final PersonRepository personRepository;

    // Открытие нового вклада
    @Override
    public Deposit openNewDeposit(Deposit deposit) {

        Deposit newDeposit = Deposit.builder()
                .amount(deposit.getAmount())
                .interestRate(deposit.getInterestRate())
                .currency(deposit.getCurrency())
                .depositNumber(UUID.randomUUID().toString())
                .status(Status.ACTIVE)
                .startDate(new Date())
                .build();

        return depositRepository.save(newDeposit);
    }

    // Закрытие вклада
    @Override
    public Deposit closeDeposit(Deposit deposit) {
        return null;
    }
}
=======
package com.example.crm.service.impl;

import com.example.crm.entity.Person;
import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Status;
import com.example.crm.repository.DepositRepository;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {
    private final DepositRepository depositRepository;
    private final PersonRepository personRepository;

    // Открытие нового вклада
    @Override
    public Deposit openNewDeposit(Deposit deposit) {

        Deposit newDeposit = Deposit.builder()
                .amount(deposit.getAmount())
                .interestRate(deposit.getInterestRate())
                .currency(deposit.getCurrency())
                .depositNumber(UUID.randomUUID().toString())
                .status(Status.ACTIVE)
                .startDate(new Date())
                .build();

        return depositRepository.save(newDeposit);
    }

    // Закрытие вклада
    @Override
    public Deposit closeDeposit(Deposit deposit) {
        return null;
    }
}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
