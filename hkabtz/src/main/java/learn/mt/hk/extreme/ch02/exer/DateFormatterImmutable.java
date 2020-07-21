package learn.mt.hk.extreme.ch02.exer;

import net.jcip.annotations.ThreadSafe;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Exercise 2.4: "Immutable Date Parsing":
 * Solve thread safety by using the immutable parser object
 * DateTimeFormatter.ISO_LOCAL_DATE.
 * Your methods would now use LocalDate.
 */
@ThreadSafe
public class DateFormatterImmutable implements ConfinedDateFormatter {
    private static final DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public String format(Date date) {
        LocalDate d = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return df.format(d);
    }

    @Override
    public Date parse(String date) {
        LocalDate localDate = LocalDate.parse(date, df);
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
