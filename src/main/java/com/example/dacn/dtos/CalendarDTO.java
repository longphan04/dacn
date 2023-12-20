package com.example.dacn.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class CalendarDTO {
    private Double depositAmount;
    private Double withdrawAmount;
    private Double sumAmount;
    private Date date;
}
