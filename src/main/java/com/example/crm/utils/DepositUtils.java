package com.example.crm.utils;

import com.example.crm.entity.Person;
import com.example.crm.entity.products.Deposit;
import com.example.crm.dto.DepositDto;
import com.example.crm.enums.Currency;
import com.example.crm.repository.DepositRepository;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.deposit.ValidationDeposit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class DepositUtils {
    private final DepositRepository depositRepository;
    private final PersonRepository personRepository;

    // Определение даты окончания вклада
    public LocalDateTime calculateEndDate(LocalDateTime startDate, Integer termInDays) {
        LocalDateTime endDateTime = startDate.plusDays(termInDays);
        endDateTime.with(LocalTime.MIDNIGHT);
        return endDateTime;
    }

    // Генерация номера вклада
    public String generateDepositNumber(Currency currency) {
        String lastDepositNumber = depositRepository.findTopByOrderByIdDesc()
                .map(Deposit::getDepositNumber)
                .orElse("P0");

        int lastNumber = Integer.parseInt(lastDepositNumber.substring(1));

        return validateCurrency(currency) + (lastNumber + 1);
    }

    // Определение валюты вклада
    public String validateCurrency(Currency currency) {
        return switch (currency.name()) {
            case "RUB" -> "P";
            case "USD" -> "U";
            case "EUR" -> "E";
            default -> throw new IllegalArgumentException("Unsupported currency: " + currency.name());
        };
    }

    // Расчет сколько клиент получит со вклада
    private Double calculateInterest(DepositDto depositDto) {
        double amount = depositDto.getAmount();
        double interestRate = depositDto.getInterestRate() / 100;
        int termDays = depositDto.getDepositTermDays();

        return (amount * interestRate * termDays) / 365;
    }

    // Пополнение средств у клиента
    public void replenishmentBalance(Person depositHolder, double amount) {
        depositHolder.setBalance(depositHolder.getBalance() + amount);

        personRepository.save(depositHolder);
    }

    // Списание средств у клиента
    public void deductBalance(Person depositHolder, double amount) {
        depositHolder.setBalance(depositHolder.getBalance() - amount);
        personRepository.save(depositHolder);
    }
}