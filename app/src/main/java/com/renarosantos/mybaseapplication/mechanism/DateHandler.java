package com.renarosantos.mybaseapplication.mechanism;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by renarosantos on 18/05/16.
 */
public class DateHandler {

    private static final String DEFAULT_TIME_ZONE_STRING = "GMT";
    private static final String BRAZILIAN_TIME_ZONE_STRING = "GMT-3";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final int DATE_FORMAT_SIZE = 10;

    /**
     * @param defaultDateFormat
     *         following default date format "yyyy-MM-dd"
     *
     * @return formatted string following the format dd/MM/yyyy or the same date input 'defaultDateFormat' case format
     * is not correct
     */
    public static String convertLocalDateFormat(@NonNull final String defaultDateFormat) {
        if (defaultDateFormat.length() == DATE_FORMAT_SIZE) {
            return defaultDateFormat.substring(8, 10) + "/" + defaultDateFormat.substring(5, 7) + "/" +
                    defaultDateFormat.substring(0, 4);
        } else {
            return defaultDateFormat;
        }
    }

    /**
     * @return the date now, following the patter yyyy-MM-dd
     */
    public static String date() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(BRAZILIAN_TIME_ZONE_STRING));
        SimpleDateFormat format1 = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.getDefault());
        return format1.format(cal.getTime());
    }

    public static String dateInFirstDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(BRAZILIAN_TIME_ZONE_STRING));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat format1 = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.getDefault());
        return format1.format(cal.getTime());
    }

    /**
     * @param baseDate
     *         should be in format yyyy-MM-dd
     *
     * @return
     */
    public static boolean isLaterThanToday(@NonNull final String baseDate) {
        if (baseDate.length() == DATE_FORMAT_SIZE) {
            final int year = Integer.parseInt(baseDate.substring(0, 4));
            final int month = Integer.parseInt(baseDate.substring(5, 7)) - 1;
            final Calendar inputCalendar = Calendar.getInstance();
            inputCalendar.setTimeZone(TimeZone.getTimeZone(BRAZILIAN_TIME_ZONE_STRING));
            inputCalendar.set(year, month, 1);

            final long todayTimeStamp =
                    Calendar.getInstance(TimeZone.getTimeZone(BRAZILIAN_TIME_ZONE_STRING)).getTimeInMillis() / 1000;
            final long baseDateTimeStamp = inputCalendar.getTimeInMillis() / 1000;

            return baseDateTimeStamp > todayTimeStamp;
        } else {
            return false;
        }
    }


    public static String getLastDayOfMonth(@NonNull final String baseDate) {
        if (baseDate.length() == DATE_FORMAT_SIZE) {
            final int year = Integer.parseInt(baseDate.substring(0, 4));
            final int month =
                    Integer.parseInt(baseDate.substring(5, 7)); //months are required to be zero based, so JAN = 0
            final Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone(BRAZILIAN_TIME_ZONE_STRING));
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(year, month - 1, lastDay);
            final SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.getDefault());
            return format.format(cal.getTime());
        } else {
            return baseDate;
        }
    }

    /*
    * Expect a baseDate string as yyyy-MM-dd, returns the base date + 1 month, following the pattern yyyy-MM-dd
    * */
    public static String getNextMonth(@NonNull final String baseDate) {
        if (baseDate.length() == DATE_FORMAT_SIZE) {
            final int year = Integer.parseInt(baseDate.substring(0, 4));
            final int month =
                    Integer.parseInt(baseDate.substring(5, 7)); //months are required to be zero based, so JAN = 0
            final Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone(BRAZILIAN_TIME_ZONE_STRING));
            if (month == 12) {
                cal.set(year + 1, 0, 1);
            } else {
                cal.set(year, month, 1);
            }
            final SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.getDefault());
            return format.format(cal.getTime());
        } else {
            return baseDate;
        }
    }

    /*
    * Expect a baseDate string as yyyy-MM-dd, returns the base date - 1 month, following the pattern yyyy-MM-dd
    * */
    public static String getPreviousMonth(@NonNull final String baseDate) {
        if (baseDate.length() == DATE_FORMAT_SIZE) {
            final int year = Integer.parseInt(baseDate.substring(0, 4));
            final int month =
                    Integer.parseInt(baseDate.substring(5, 7)) - 1;//months are required to be zero based, so JAN = 0
            final Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone(BRAZILIAN_TIME_ZONE_STRING));
            if (month == 0) {
                cal.set(year - 1, 11, 1);
            } else {
                cal.set(year, month - 1, 1);
            }
            final SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.getDefault());
            return format.format(cal.getTime());
        } else {
            return baseDate;
        }
    }

    @Nullable
    public static Double getDouble(String string) {
        Double result = null;
        try {
            result = Double.valueOf(string);
        } catch (NumberFormatException nfe) {
            // do nothing
        }
        return result;
    }

    @Nullable
    public static Float getFloat(String string) {
        Float result = null;
        try {
            result = Float.valueOf(string);
        } catch (NumberFormatException nfe) {
            // do nothing
        }
        return result;
    }

    @Nullable
    public static Integer getInteger(String string) {
        Integer result = null;
        try {
            result = Integer.valueOf(string);
        } catch (NumberFormatException nfe) {
            //do nothing
        }
        return result;
    }

    @NonNull
    public static Long getTimeStamp() {
        return Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIME_ZONE_STRING)).getTimeInMillis() / 1000;
    }



    public static int getDay(@NonNull final String date) {
        if (TextUtils.isEmpty(date) || date.length() != DATE_FORMAT_SIZE) {
            return 1;
        } else {
            final String dayValue = date.substring(8, 10);
            try {
                return Integer.valueOf(dayValue);
            } catch (NumberFormatException e) {
                return 1;
            }
        }
    }
}
