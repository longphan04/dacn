package com.example.dacn.services;

import com.example.dacn.dtos.CalendarDTO;
import com.example.dacn.repositories.DepositRepository;
import com.example.dacn.repositories.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CalendarService {
    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private WithdrawRepository withdrawRepository;

    public CalendarDTO getCalendarInfo(CalendarDTO calendarDTO) {
        int userId = calendarDTO.getUserId();
        Date date = calendarDTO.getDate();

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        Double depositAmount = depositRepository.getDepositsInYear(userId, year, month, day);
        calendarDTO.setDepositAmount(depositAmount == null ? 0 : depositAmount);

        Double withdrawAmount = withdrawRepository.getWithdrawsInYear(userId, year, month, day);
        calendarDTO.setWithdrawAmount(withdrawAmount == null ? 0 : withdrawAmount);

        Double sumAmount = calendarDTO.getDepositAmount() - calendarDTO.getWithdrawAmount();
        calendarDTO.setSumAmount(sumAmount);
        return calendarDTO;
    }
}
