package learn.mt.hk.extreme.ch02.exer;

import net.jcip.annotations.NotThreadSafe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Source class for
 *
 * Exercise 2.1 "Thread Confined Date Parsing":
 * Make the DateFormat field thread confined.
 * Record how much object have been generated.
 * Record how long does it take and compare with the next exercises.
 *
 * Exercise 2.2 "Stack Confined Date Parsing":
 * Make the DateFormat field stack confined.
 *
 * Exercise 2.3: "Object Confined Date Parsing":
 * Make the Date Format field object confined.
 *
 * Exercise 2.4: "Immutable Date Parsing":
 * Solve thread safety by using the immutable parser object
 * DateTimeFormatter.ISO_LOCAL_DATE.
 * Your methods would now use LocalDate.
 */
@SuppressWarnings("unused")
@NotThreadSafe
public class DateFormatter {
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public String format(Date date) {
        return df.format(date);
    }

    public Date parse(String date) throws ParseException {
        return df.parse(date);
    }
}
