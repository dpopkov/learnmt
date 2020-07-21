package learn.mt.hk.extreme.ch02.exer;

import net.jcip.annotations.ThreadSafe;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Exercise 2.1 "Thread Confined Date Parsing":
 * Make the DateFormat field thread confined.
 * Record how much object have been generated.
 * Record how long does it take and compare with the next exercises.
 */
@ThreadSafe
public class DateFormatterThreadConfined implements ConfinedDateFormatter {
    private static final ThreadLocal<DateFormat> localFormat =
            ThreadLocal.withInitial(DateFormatCounted::new);

    @Override
    public String format(Date date) {
        return localFormat.get().format(date);
    }

    @Override
    public Date parse(String date) throws ParseException {
        return localFormat.get().parse(date);
    }
}
