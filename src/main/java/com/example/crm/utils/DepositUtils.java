package com.example.crm.utils;

import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Currency;
import com.example.crm.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class DepositUtils {
    private final DepositRepository depositRepository;

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

//    public Double calculateInterest(Deposit deposit) {
//        LocalDateTime currentDate = LocalDateTime.now();
//
//        if (currentDate.isAfter(deposit.getEndDate())) {
//
//        }
//    }
}
