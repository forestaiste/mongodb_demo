package com.forest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String dateToString(String pattern, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String pattern, String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Date d = null;

        try {
            d = simpleDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }
}
