package com.meloafc.estacionamento.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public final class DateTimeUtils {

    public static final int LENGTH_TIME_HHMM = 5;

    public static final long SEGUNDO = 1000;
    public static final long MINUTO = 60 * SEGUNDO;
    public static final long HORA = 60 * MINUTO;

    public static BigDecimal convertMillisecondsToHours(long milliseconds) {
        return BigDecimal.valueOf(milliseconds).divide(BigDecimal.valueOf(HORA), 2, RoundingMode.HALF_UP);
    }

    public static Date convertStringHHMMSSToDate(String stringDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.parse(stringDate);
    }

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

    public static Time convert(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return convert(calendar);
    }

    public static Time convert(Calendar calendar) {
        LocalTime localTime = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));
        return Time.valueOf(localTime);
    }

}
