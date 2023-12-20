package com.example.dacn.dtos;

import lombok.Data;

import java.sql.Date;

@Data
public class DepositDTO {
    private Date date;
    private Double amount;
    private String description;
    private Long categoryId;
}