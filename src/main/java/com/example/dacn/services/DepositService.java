package com.example.dacn.services;

import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.entities.Category;
import com.example.dacn.entities.Deposit;
import com.example.dacn.entities.User;
import com.example.dacn.repositories.DepositRepository;
import com.example.dacn.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public DepositDTO save(DepositDTO depositDTO) {
        Deposit deposit = new Deposit();
        deposit.setDate(DateTimeUtil.convertDateToTimeStamp(depositDTO.getDate()));
        deposit.setAmount(depositDTO.getAmount());
        deposit.setDescription(depositDTO.getDescription());
        deposit.setUser(User.builder().id(userService.getLoginUserId()).build());
        deposit.setCategory(Category.builder().id(depositDTO.getCategoryId()).build());
        depositRepository.save(deposit);
        return depositDTO;
    }

    public List<DepositDTO> getAllDepositsOfCurrentUser() {
        String username = userService.getLoginUsername();
        List<Deposit> deposits = depositRepository.findAllByUsername(username);
        return this.convertToDepositDTO(deposits);
    }

    public Double getSumAllDepositOfCurrentUser() {
        String username = userService.getLoginUsername();
        return depositRepository.getSumAllDepositOfCurrentUser(username);
    }

    public Double getCurrentMonthDepositAmount() {
        String username = userService.getLoginUsername();
        return depositRepository.getCurrentMonthDepositAmount(username);
    }

    public List<DepositDTO> getTopDepositsOfThisMonth() {
        String username = userService.getLoginUsername();
        return convertToDepositDTO(depositRepository.getTopDepositsOfThisMonth(username));
    }

    private List<DepositDTO> convertToDepositDTO(List<Deposit> deposits) {
        return deposits.stream().map(deposit -> DepositDTO.builder()
                .amount(deposit.getAmount())
                .date(DateTimeUtil.convertTimestampToDate(deposit.getDate()))
                .description(deposit.getDescription())
                .categoryDTO(categoryService.convertToCategoryDTO(deposit.getCategory()))
                .build()).collect(Collectors.toList());
    }
}
