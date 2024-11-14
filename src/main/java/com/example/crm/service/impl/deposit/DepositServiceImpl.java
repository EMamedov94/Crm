package com.example.crm.service.impl.deposit;


import com.example.crm.dto.DepositDto;
import com.example.crm.entity.Person;
import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Status;
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

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {
    private final DepositRepository depositRepository;
    private final PersonRepository personRepository;
    private final ValidationDeposit validationDeposit;
    private final DepositUtils depositUtils;
    private final DateUtils dateUtils;

    // Открытие нового вклада
    @Override
    @Transactional
    public Deposit openNewDeposit(DepositDto depositDto) {

        // Получить клиента и проверить наличие его средств
        Person depositHolder = getDepositHolderWithSufficientBalance(depositDto);

        // Списание ДС у клиента
        deductBalance(depositHolder, depositDto.getAmount());

        // Открытие депозита
        Deposit newDeposit = createNewDeposit(depositDto, depositHolder);

        // Проверить баланс клиента
        validationDeposit.validateBalance(depositHolder.getId(), depositDto.getAmount());

        // Обновить баланс клиента
        depositHolder.setBalance(depositHolder.getBalance() - depositDto.getAmount());
        personRepository.save(depositHolder);

        return depositRepository.save(newDeposit);
    }

    // Закрытие вклада
    @Override
    public Deposit closeDeposit(DepositDto depositDto) {

        // Поиск депозита в БД
        Deposit depositDb = depositRepository.findById(depositDto.getId())
                .orElseThrow(() -> new RuntimeException("Депозит не найден"));

        // Получение клиента из БД и проверка его баланса
        Person depositHolder = getDepositHolderWithSufficientBalance(depositDto);

        // Пополение баланса клиента
        replenishmentBalance(depositHolder, depositDto.getAmount());

        // Закрытие депозита
        depositDb.setStatus(Status.CLOSED);

        return depositRepository.save(depositDb);
    }

    // Получение клиента и проверки его баланса
    private Person getDepositHolderWithSufficientBalance(DepositDto depositDto) {
        Person depositHolder = personRepository.findById(depositDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        validationDeposit.validateBalance(depositHolder.getId(), depositDto.getAmount());
        return depositHolder;
    }

    // Пополнение средств у клиента
    private void replenishmentBalance(Person depositHolder, double amount) {
        depositHolder.setBalance(depositHolder.getBalance() + amount);

        personRepository.save(depositHolder);
    }

    // Списание средств у клиента
    private void deductBalance(Person depositHolder, double amount) {
        depositHolder.setBalance(depositHolder.getBalance() - amount);
        personRepository.save(depositHolder);
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
