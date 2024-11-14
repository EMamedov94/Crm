package com.example.crm.configuration;

import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Status;
import com.example.crm.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {
    private final DepositRepository depositRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Выполнять каждый день в полночь
    public void closeExpiredDeposits() {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Deposit> expiredDeposits = depositRepository.findByEndDateBeforeAndStatus(currentDate, Status.ACTIVE);

        for (Deposit deposit : expiredDeposits) {
            deposit.setStatus(Status.CLOSED);

            depositRepository.save(deposit);
        }
    }
}
