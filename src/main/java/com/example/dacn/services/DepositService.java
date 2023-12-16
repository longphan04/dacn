package com.example.dacn.services;

import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.entities.Deposit;
import com.example.dacn.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;

    public DepositDTO save(DepositDTO depositDTO) {
        Deposit deposit = new Deposit();
        deposit.setDate(new Timestamp(depositDTO.getDate().getTime()));
        deposit.setAmount(depositDTO.getAmount());
        deposit.setDescription(depositDTO.getDescription());
        deposit.setUserId(depositDTO.getUserId());
        deposit.setCategoryId(depositDTO.getCategoryId());
        depositRepository.save(deposit);
        return depositDTO;
    }
}
