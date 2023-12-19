package com.example.dacn.controllers;

import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.dtos.WithdrawDTO;
import com.example.dacn.services.DepositService;
import com.example.dacn.services.WithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("finance")
@RequiredArgsConstructor
public class FinanceController {
    private final DepositService depositService;
    private final WithdrawService withdrawService;

    @PostMapping("deposit")
    public String deposit(@ModelAttribute DepositDTO depositDTO, Model model) {
        DepositDTO deposit = depositService.save(depositDTO);
        model.addAttribute("deposit", deposit);
        return "deposit";
    }

    @PostMapping("withdraw")
    public String withdraw(@ModelAttribute WithdrawDTO withdrawDTO, Model model) {
        WithdrawDTO withdraw = withdrawService.save(withdrawDTO);
        model.addAttribute("withdraw", withdraw);
        return "withdraw";
    }
}
