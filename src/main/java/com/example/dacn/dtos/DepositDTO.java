package com.example.dacn.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class DepositDTO {
    private Date date;
    private Double amount;
    private String description;
    private Long categoryId;
    private CategoryDTO categoryDTO;
}