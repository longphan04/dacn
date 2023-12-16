package com.example.dacn.services;

import com.example.dacn.dtos.WithdrawDTO;
import com.example.dacn.entities.Withdraw;
import com.example.dacn.repositories.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class WithdrawService {
    @Autowired
    private WithdrawRepository withdrawRepository;

    public WithdrawDTO save(WithdrawDTO withdrawDTO) {
        Withdraw withdraw = new Withdraw();
        withdraw.setDate(new Timestamp(withdrawDTO.getDate().getTime()));
        withdraw.setAmount(withdrawDTO.getAmount());
        withdraw.setDescription(withdrawDTO.getDescription());
        withdraw.setUserId(withdrawDTO.getUserId());
        withdraw.setCategoryId(withdrawDTO.getCategoryId());
        withdrawRepository.save(withdraw);
        return withdrawDTO;
    }
}
