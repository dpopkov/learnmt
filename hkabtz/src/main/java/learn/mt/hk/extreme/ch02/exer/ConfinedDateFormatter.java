package learn.mt.hk.extreme.ch02.exer;

import java.text.ParseException;
import java.util.Date;

public interface ConfinedDateFormatter {
    String format(Date date);

    Date parse(String date) throws ParseException;
}
