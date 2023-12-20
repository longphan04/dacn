package com.example.dacn.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class StatisticalDTO {
    private Double depositAmount;
    private Double withdrawAmount;
    private Double sumAmount;
    private Date date;
}
