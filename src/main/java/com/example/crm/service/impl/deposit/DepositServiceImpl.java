package com.example.crm.service.impl.deposit;


import com.example.crm.dto.DepositDto;
import com.example.crm.entity.Person;
import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Status;
import com.example.crm.exception.*;
import com.example.crm.repository.DepositRepository;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.deposit.DepositService;
import com.example.crm.service.deposit.ValidationDeposit;
import com.example.crm.utils.DateUtils;
import com.example.crm.utils.DepositUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {
    private final DepositRepository depositRepository;
    private final PersonRepository personRepository;
    private final ValidationDeposit validationDeposit;
    private final DepositUtils depositUtils;
    private final DateUtils dateUtils;

    // Открытие нового Депозита
    @Override
    @Transactional
    public Deposit openNewDeposit(DepositDto depositDto) {

        // Поиск клиента в бд
        Person depositHolder = personRepository.findById(depositDto.getClientId())
                .orElseThrow(UserNotFoundException::new);

        // Проверка баланса клиента
        validationDeposit.validateBalance(depositDto.getAmount(), depositHolder.getBalance());

        // Списание баланса
        depositUtils.deductBalance(depositHolder, depositDto.getAmount());

        // Открытие депозита
        Deposit newDeposit = createNewDeposit(depositDto, depositHolder);

        return depositRepository.save(newDeposit);
    }

    // Закрытие вклада
    @Override
    @Transactional
    public Deposit closeDeposit(DepositDto depositDto) {

        // Поиск депозита в БД и проверка его статуса
        Deposit depositDb = validationDeposit.findDepositAndValidateStatus(depositDto);

        double interestAmount = dateUtils.isEarlyClosure(depositDb) ?
                depositUtils.calculateEarlyClosureInterest(depositDb) :
                depositUtils.calculateInterest(depositDto);

        // Закрытие депозита
        depositDb.setStatus(Status.CLOSED);

        // Пополение баланса клиента
        depositUtils.replenishmentBalance(depositDb.getDepositHolder(), depositDb.getAmount() + interestAmount);

        return depositRepository.save(depositDb);
    }

    // Расчет и отображение суммы потерянных % если депозит закрывается раньше времени
    @Override
    public Double calculateClosureAmount(DepositDto depositDto) {

        // Поиск депозита в БД и проверка его статуса
        Deposit depositDb = validationDeposit.findDepositAndValidateStatus(depositDto);



        return dateUtils.calculateLostAmount(depositDb);
    }

    // Поиск депозитов по id клиента
    @Override
    public List<Deposit> findDepositsByClientId(Long clientId) {
        return validationDeposit.validateClientAndDeposits(clientId);
    }

    // Создание нового депозита
    private Deposit createNewDeposit(DepositDto depositDto, Person depositHolder) {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = dateUtils.calculateEndDate(startDate, depositDto.getDepositTermDays());

        return Deposit.builder()
                .amount(depositDto.getAmount())
                .interestRate(depositDto.getInterestRate())
                .currency(depositDto.getCurrency())
                .depositNumber(depositUtils.generateDepositNumber(depositDto.getCurrency()))
                .depositTermDays(depositDto.getDepositTermDays())
                .status(Status.ACTIVE)
                .startDate(startDate)
                .endDate(endDate)
                .depositHolder(depositHolder)
                .build();
    }
}
