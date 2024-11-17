package com.example.crm.utils;

import com.example.crm.entity.products.Deposit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class DateUtils {

    // Получение дней до конца депозита
    public LocalDateTime calculateEndDate(LocalDateTime startDate, Integer termInDays) {
        LocalDateTime endDateTime = startDate.plusDays(termInDays);
        endDateTime.with(LocalTime.MIDNIGHT);
        return endDateTime;
    }

    // Получение дней сколько пролежал депозит
    private Integer calculateDepositDays(LocalDateTime startDate) {
        long days = Duration.between(startDate, LocalDateTime.now()).toDays();
        return (int) days;
    }

    // Получение суммы при досрочном закрытии депозита
    public Double calculateLostAmount(Deposit deposit) {
        BigDecimal amount = BigDecimal.valueOf(deposit.getAmount());
        BigDecimal interestRate = BigDecimal.valueOf(deposit.getInterestRate());
        int depositDays = calculateDepositDays(deposit.getStartDate());

        BigDecimal result = amount.multiply(interestRate).multiply(new BigDecimal(depositDays))
                .divide(new BigDecimal(365), 2, RoundingMode.HALF_UP);

        return result.doubleValue();
    }

    // Расчет депозита при досрочном закрытии по ставке 0.1%
    public Double calculateInterest(Deposit deposit) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime endDate = deposit.getEndDate();
        double sum = 0.0;

        if (currentDate.isBefore(endDate)) {
            sum = deposit.getAmount() * 0.001 * calculateDepositDays(deposit.getStartDate()) / 365;
        }

        return sum;
    }
}
