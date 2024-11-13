package com.example.crm.utils;

<<<<<<< HEAD
import com.example.crm.entity.products.Deposit;
=======
import com.example.crm.dto.DepositDto;
import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Currency;
>>>>>>> 4987140bc5001d74c74d3ac138388ade3ab37ec0
import com.example.crm.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
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
=======
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

    // Расчет сколько клиент получит со вклада
    public Double calculateInterest(DepositDto depositDto) {
        double amount = depositDto.getAmount();
        double interestRate = depositDto.getInterestRate() / 100;
        int termDays = depositDto.getDepositTermDays();

        return (amount * interestRate * termDays) / 365;
    }

    public Double closeDepositEarly(DepositDto depositDto) {
        LocalDateTime today = LocalDateTime.now();

        // Вычисление оставшихся дней до закрытия депозита
        long daysRemaining = ChronoUnit.DAYS.between(today, endDate);
>>>>>>> 4987140bc5001d74c74d3ac138388ade3ab37ec0
    }
}
