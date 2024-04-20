package com.travel.vietnamtravel.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

import static com.travel.vietnamtravel.util.DataUtil.isNullOrEmpty;

public class DateTimeConvert {
    public static String dateTimeToString(LocalDateTime date, String pattern) {
        if (date == null || isNullOrEmpty(pattern)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(pattern, new Locale("vi"));
        return df.format(date);
    }

    public static String dateToString(LocalDate date, String pattern) {
        if (date == null || isNullOrEmpty(pattern)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(pattern, new Locale("vi"));
        return df.format(date);
    }
}
