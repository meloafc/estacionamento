package com.meloafc.estacionamento.utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeUtils {

    public static final int LENGTH_TIME_HHMM = 5;

    public static String convertDateToHHMM(Date date) {
        if (date != null) {
            return new SimpleDateFormat("HH:mm").format(date);
        }
        return null;
    }

    public static Date convertStrHHMMToDate(String time) {
        time += ":00";
        return Time.valueOf(time);
    }

}
