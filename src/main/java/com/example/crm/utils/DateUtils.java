package com.example.crm.utils;

import com.example.crm.entity.products.Deposit;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
public class DateUtils {

    public static LocalDateTime calculateEndDate(LocalDateTime startDate, Integer termInDays) {
        LocalDateTime endDateTime = startDate.plusDays(termInDays);
        endDateTime.with(LocalTime.MIDNIGHT);
        return endDateTime;
    }

//    public static Double calculateInterest(Deposit deposit) {
//        LocalDateTime currentDate = LocalDateTime.now();
//
//        if (currentDate.isAfter(deposit.getEndDate())) {
//
//        }
//    }
}
