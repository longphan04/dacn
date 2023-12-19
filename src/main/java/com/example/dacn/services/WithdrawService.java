package com.example.dacn.services;

import com.example.dacn.dtos.WithdrawDTO;
import com.example.dacn.entities.Withdraw;
import com.example.dacn.repositories.WithdrawRepository;
import com.example.dacn.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawService {
    private final WithdrawRepository withdrawRepository;
    private final UserService userService;

    public WithdrawDTO save(WithdrawDTO withdrawDTO) {
        Withdraw withdraw = new Withdraw();
        withdraw.setDate(DateTimeUtil.convertToTimeStamp(withdrawDTO.getDate()));
        withdraw.setAmount(withdrawDTO.getAmount());
        withdraw.setDescription(withdrawDTO.getDescription());
        withdraw.setUserId(userService.getLoginUserId());
        withdraw.setCategoryId(withdrawDTO.getCategoryId());
        withdrawRepository.save(withdraw);
        return withdrawDTO;
    }
}
