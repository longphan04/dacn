package com.example.dacn.utils;

import java.sql.Timestamp;
import java.util.Date;

public class DateTimeUtil {
    public static Timestamp convertToTimeStamp(Date date) {
        return new Timestamp(date.getTime());
    }
}
