package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Task2 {
    public static ArrayList<Date> allFridays13th (int year) {
        ArrayList<Date> result = new ArrayList<>();
        if (year < 0) throw new IllegalArgumentException();
        Date dt = new Date(year, 1, 1);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        if (c.getFirstDayOfWeek() > 6) {
            c.add(Calendar.DATE, 6);
        } else {
            c.add(Calendar.DATE, 6 - c.getFirstDayOfWeek());
        }
        while (true) {
            var curr = c.getTime();
            if (curr.getDay() == 13 && c.get(Calendar.DAY_OF_WEEK) == 6) {
                result.add(curr);
            }
            if (curr.getMonth() == 12 && curr.getDay() >= 25)  {
                break;
            }
            c.add(Calendar.DATE, 7);
        }
        return result;
    }

    public static Date next(int year, int month, int day) {
        var variant1 = LocalDate.of(year, month, day).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        while (variant1.getDayOfYear() != 13) {
            variant1 = variant1.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        }
        return new Date(variant1.getYear(),variant1.getMonthValue(),variant1.getDayOfMonth());
    }
}
