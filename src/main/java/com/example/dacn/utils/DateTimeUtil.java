package com.example.dacn.utils;

import java.sql.Timestamp;
import java.util.Date;

public class DateTimeUtil {
    public static Timestamp convertDateToTimeStamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static Date convertTimestampToDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }
}
