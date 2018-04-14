package com.edu.task1_JDBC.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by damager on 25.01.18.
 */
public class DateHelper{
    private static Calendar calendar = new GregorianCalendar();
    private static SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");

    public static String fistWeek(Date date) {
        calendar.setTime(date);

        Integer dayDifference = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? -6 : (Calendar.MONDAY - calendar.get(Calendar.DAY_OF_WEEK));
        calendar.add(Calendar.DAY_OF_MONTH, dayDifference);
        String dateFirstDayOfWeek = formattedDate.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -dayDifference);

        return dateFirstDayOfWeek;
    }


    public static String fistMonth(Date date) {
        calendar.setTime(date);

        Integer dayDifference = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? -6 : (Calendar.MONDAY - calendar.get(Calendar.DAY_OF_WEEK));
        calendar.add(Calendar.DAY_OF_MONTH, dayDifference);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String dateFirstDayOfMonth = formattedDate.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -dayDifference);

        return dateFirstDayOfMonth;
    }


    public static String fistQuarter(Date date) {
        calendar.setTime(date);

        Integer monthDifference = 0;
        if (calendar.get(Calendar.MONTH) == Calendar.JANUARY || calendar.get(Calendar.MONTH) == Calendar.APRIL ||
                calendar.get(Calendar.MONTH) == Calendar.JULY || calendar.get(Calendar.MONTH) == Calendar.OCTOBER) {
            monthDifference = 0;
        } else if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY || calendar.get(Calendar.MONTH) == Calendar.MAY ||
                calendar.get(Calendar.MONTH) == Calendar.AUGUST || calendar.get(Calendar.MONTH) == Calendar.NOVEMBER) {
            monthDifference = -1;
        } else if (calendar.get(Calendar.MONTH) == Calendar.MARCH || calendar.get(Calendar.MONTH) == Calendar.JUNE ||
                calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER || calendar.get(Calendar.MONTH) == Calendar.DECEMBER) {
            monthDifference = -2;
        } else {
            monthDifference = 0;
        }
        calendar.add(Calendar.MONTH, monthDifference);
        String dateFirstDayOfQuarter = fistMonth(calendar.getTime());

        return dateFirstDayOfQuarter;
    }

    public static String fistYaer(Date date) {
        calendar.setTime(date);

        Integer dayDifference = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? -6 : (Calendar.MONDAY - calendar.get(Calendar.DAY_OF_WEEK));
        calendar.add(Calendar.DAY_OF_MONTH, dayDifference);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        String dateFirstDayOfYear = formattedDate.format(calendar.getTime());

        return dateFirstDayOfYear;
    }

    public static String dateToString(Date date){
        calendar.setTime(date);
        String dateToday = formattedDate.format(calendar.getTime());

        return dateToday;
    }

    public static String predDate(Date date){
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String dateYesterday = formattedDate.format(calendar.getTime());

        return dateYesterday;
    }

    public static Date randomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1900, 2010);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        Date date = gc.getTime();

        return date;
    }

    private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
