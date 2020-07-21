package learn.mt.hk.extreme.ch02.exer;

import learn.mt.hk.extreme.ch02.Counter;

import java.text.SimpleDateFormat;

/**
 * Extension of {@link SimpleDateFormat} that counts number of instances.
 */
class DateFormatCounted extends SimpleDateFormat {
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    private static final Counter numInstances = new Counter();

    public DateFormatCounted() {
        super(DATE_FORMAT_PATTERN);
        numInstances.increment();
    }

    public static long getNumInstances() {
        return numInstances.getValue();
    }
}
