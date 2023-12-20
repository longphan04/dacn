package com.example.dacn.services;

import com.example.dacn.dtos.CalendarDTO;
import com.example.dacn.repositories.DepositRepository;
import com.example.dacn.repositories.WithdrawRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final DepositRepository depositRepository;
    private final WithdrawRepository withdrawRepository;
    private final UserService userService;

    public CalendarDTO getCalendarInfo(CalendarDTO calendarDTO) {
        String username = userService.getLoginUsername();
        Date date = calendarDTO.getDate();
        if (date == null) {
            date = Date.from(Instant.now());
        }

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        Double depositAmount = depositRepository.getDepositsOnDay(username, year, month, day);
        calendarDTO.setDepositAmount(depositAmount == null ? 0 : depositAmount);

        Double withdrawAmount = withdrawRepository.getWithdrawsOnDay(username, year, month, day);
        calendarDTO.setWithdrawAmount(withdrawAmount == null ? 0 : withdrawAmount);

        Double sumAmount = calendarDTO.getDepositAmount() - calendarDTO.getWithdrawAmount();
        calendarDTO.setSumAmount(sumAmount);
        calendarDTO.setDate(date);

        return calendarDTO;
    }
}
