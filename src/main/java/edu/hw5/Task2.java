package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Task2 {
    public static ArrayList<Date> allFridays13th(int year) {
        ArrayList<Date> result = new ArrayList<>();
        if (year < 0) {
            throw new IllegalArgumentException();
        }
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.JANUARY, 1);
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            c.add(Calendar.DATE, 2 * 2 + 1 );
        } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            c.add(Calendar.DATE, 2 * 2 + 2);
        } else {
            c.add(Calendar.DATE, Calendar.FRIDAY - c.get(Calendar.DAY_OF_WEEK));
        }
        while (true) {
            var ofWeek = c.get(Calendar.DAY_OF_WEEK);
            if (c.get(Calendar.DATE) == 2 * 2 * 2 + 2 * 2 + 1 && ofWeek == Calendar.FRIDAY) {
                result.add(c.getTime());
            }
            if (c.get(Calendar.MONTH) == Calendar.DECEMBER && c.get(Calendar.DATE) >= 2 * 2 * 2 + 2 * 2 + 1) {
                break;
            }
            c.add(Calendar.DATE, 2 * 2 + 2 * 2 - 1);
        }
        return result;
    }

    public static LocalDate next(int year, int month, int day) {
        if (year < 0) {
            throw new IllegalArgumentException();
        }
        var variant1 = LocalDate.of(year, month, day).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        //variant1 = variant1.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (variant1.getDayOfMonth() != 13) {
            variant1 = variant1.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        //Calendar cal = Calendar.getInstance();
        //cal.set(Calendar.YEAR, variant1.getYear());
        //cal.set(Calendar.MONTH, variant1.getMonthValue());
        //cal.set(Calendar.DAY_OF_MONTH, variant1.getDayOfMonth());
        return variant1;
    }
}
