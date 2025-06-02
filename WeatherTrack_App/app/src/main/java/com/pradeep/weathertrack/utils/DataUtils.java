package com.pradeep.weathertrack.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    
    public static String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    
    public static String formatTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    
    public static String formatDateTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATETIME_FORMAT, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    
    public static long getStartOfDay(long timestamp) {
        long dayInMs = Constants.MILLISECONDS_IN_DAY;
        return (timestamp / dayInMs) * dayInMs;
    }
    
    public static long getDaysAgo(int days) {
        return System.currentTimeMillis() - (days * Constants.MILLISECONDS_IN_DAY);
    }
}