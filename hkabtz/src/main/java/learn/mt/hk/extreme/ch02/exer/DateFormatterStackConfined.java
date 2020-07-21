package learn.mt.hk.extreme.ch02.exer;

import net.jcip.annotations.ThreadSafe;

import java.text.ParseException;
import java.util.Date;

/**
 * Exercise 2.2 "Stack Confined Date Parsing":
 * Make the DateFormat field stack confined.
 */
@ThreadSafe
public class DateFormatterStackConfined implements ConfinedDateFormatter {

    @Override
    public String format(Date date) {
        return createFormatter().format(date);
    }

    @Override
    public Date parse(String date) throws ParseException {
        return createFormatter().parse(date);
    }

    private DateFormatCounted createFormatter() {
        return new DateFormatCounted();
    }
}
