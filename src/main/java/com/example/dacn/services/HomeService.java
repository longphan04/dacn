package com.example.dacn.services;

import com.example.dacn.dtos.CategoryDTO;
import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.dtos.HomeDTO;
import com.example.dacn.dtos.WithdrawDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final DepositService depositService;
    private final WithdrawService withdrawService;

    public HomeDTO loadHomeData() {
        Double sumAllDeposit = depositService.getSumAllDepositOfCurrentUser();
        Double sumAllWithdraw = withdrawService.getSumAllWithdrawOfCurrentUser();

        Double currentMonthDepositAmount = depositService.getCurrentMonthDepositAmount();
        Double currentMonthWithdrawAmount = withdrawService.getCurrentMonthWithdrawAmount();

        List<DepositDTO> topDepositsOfThisMonth = depositService.getTopDepositsOfThisMonth();
//        while (topDepositsOfThisMonth.size() < 2) {
//            topDepositsOfThisMonth.add(DepositDTO.builder()
//                    .amount(0D)
//                    .categoryDTO(CategoryDTO.builder()
//                            .categoryType("DEPOSIT")
//                            .categoryName("N/A")
//                            .build())
//                    .build());
//        }
        List<WithdrawDTO> topWithdrawsOfThisMonth = withdrawService.getTopWithdrawsOfThisMonth();
//        while (topWithdrawsOfThisMonth.size() < 2) {
//            topWithdrawsOfThisMonth.add(WithdrawDTO.builder()
//                    .amount(0D)
//                    .categoryDTO(CategoryDTO.builder()
//                            .categoryType("WITHDRAW")
//                            .categoryName("N/A")
//                            .build())
//                    .build());
//        }

        return HomeDTO.builder()
                .currentBalance(sumAllDeposit - sumAllWithdraw)
                .depositAmount(currentMonthDepositAmount)
                .withdrawAmount(currentMonthWithdrawAmount)
                .topDeposits(this.convertToTopDeposits(topDepositsOfThisMonth, currentMonthDepositAmount))
                .topWithdraws(this.convertToTopWithdraws(topWithdrawsOfThisMonth, currentMonthWithdrawAmount))
                .build();
    }

    private List<HomeDTO.TopWithdrawDTO> convertToTopWithdraws(List<WithdrawDTO> topWithdrawsOfThisMonth, Double currentMonthWithdrawAmount) {
        return topWithdrawsOfThisMonth.stream().map(
                withdrawDTO -> HomeDTO.TopWithdrawDTO.builder()
                        .amount(withdrawDTO.getAmount())
                        .categoryName(withdrawDTO.getCategoryDTO().getCategoryName())
                        .percent(calculateAmountPercent(withdrawDTO.getAmount(), currentMonthWithdrawAmount))
                        .build()
        ).collect(Collectors.toList());
    }

    private List<HomeDTO.TopDepositDTO> convertToTopDeposits(List<DepositDTO> topDepositsOfThisMonth, Double currentMonthDepositAmount) {
        return topDepositsOfThisMonth.stream().map(
                depositDTO -> HomeDTO.TopDepositDTO.builder()
                        .amount(depositDTO.getAmount())
                        .categoryName(depositDTO.getCategoryDTO().getCategoryName())
                        .percent(calculateAmountPercent(depositDTO.getAmount(), currentMonthDepositAmount))
                        .build()
        ).collect(Collectors.toList());
    }

    private double calculateAmountPercent(Double amount, Double total) {
        if (total == 0) {
            return 0;
        }

        return amount / total * 100;
    }
}
