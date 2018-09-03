package com.chunchiehliang.networktest.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Chun-Chieh Liang on 7/21/18.
 */
public class StringUtils {
    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    public static String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from timeInMilliseconds.
     */
    public static String formatDateString(long timeInMilliseconds) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.getDefault());
        return dateFormat.format(timeInMilliseconds);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from timeInMilliseconds.
     */
    public static String formatTimeString(long timeInMilliseconds) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        return timeFormat.format(timeInMilliseconds);
    }
}
