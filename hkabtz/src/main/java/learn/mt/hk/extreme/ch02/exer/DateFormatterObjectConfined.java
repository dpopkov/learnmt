package learn.mt.hk.extreme.ch02.exer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.text.ParseException;
import java.util.Date;

/**
 * Exercise 2.3: "Object Confined Date Parsing":
 * Make the Date Format field object confined.
 */
@ThreadSafe
public class DateFormatterObjectConfined implements ConfinedDateFormatter {
    @GuardedBy("this")
    private final DateFormatCounted localFormat = new DateFormatCounted();

    @Override
    public synchronized String format(Date date) {
        return localFormat.format(date);
    }

    @Override
    public synchronized Date parse(String date) throws ParseException {
        return localFormat.parse(date);
    }
}
