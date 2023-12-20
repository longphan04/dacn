package com.example.dacn.services;

import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.entities.Deposit;
import com.example.dacn.repositories.DepositRepository;
import com.example.dacn.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;
    private final UserService userService;

    public DepositDTO save(DepositDTO depositDTO) {
        Deposit deposit = new Deposit();
        deposit.setDate(DateTimeUtil.convertToTimeStamp(depositDTO.getDate()));
        deposit.setAmount(depositDTO.getAmount());
        deposit.setDescription(depositDTO.getDescription());
        deposit.setUserId(userService.getLoginUserId());
        deposit.setCategoryId(depositDTO.getCategoryId());
        depositRepository.save(deposit);
        return depositDTO;
    }
}
