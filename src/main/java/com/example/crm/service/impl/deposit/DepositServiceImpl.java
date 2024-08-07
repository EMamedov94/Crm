package com.example.crm.service.impl.deposit;

import com.example.crm.dto.DepositDto;
import com.example.crm.entity.Person;
import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Status;
import com.example.crm.repository.DepositRepository;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.deposit.DepositService;
import com.example.crm.service.deposit.ValidationDeposit;
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

    // Открытие нового вклада
    @Override
    @Transactional
    public Deposit openNewDeposit(DepositDto depositDto) {
        Person depositHolder = personRepository.findById(depositDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Проверить баланс клиента
        validationDeposit.validateBalance(depositHolder.getId(), depositDto.getAmount());

        // Определить дату начала и окончания вклада
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = depositUtils.calculateEndDate(startDate, depositDto.getDepositTermDays());

        // Создать новый вклад
        Deposit newDeposit = Deposit.builder()
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

        // Обновить баланс клиента
        depositHolder.setBalance(depositHolder.getBalance() - depositDto.getAmount());
        personRepository.save(depositHolder);

        return depositRepository.save(newDeposit);
    }

    // Закрытие вклада
    @Override
    public Deposit closeDeposit(DepositDto depositDto) {
        Person depositHolder = personRepository.findById(depositDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Deposit depositDb = depositRepository.findById(depositDto.getId())
                .orElseThrow(() -> new RuntimeException("Депозит не найден"));

        depositDb.setStatus(Status.CLOSED);

        return depositRepository.save(depositDb);
    }
}
