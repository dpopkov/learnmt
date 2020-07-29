package learn.mt.hk.extreme.ch03.exer31;

import learn.mt.hk.extreme.util.SuperSimpleGCParser;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.stream.*;

import static org.junit.Assert.*;

/**
 * Please run with -Xloggc:some_filename.vgc
 */
@Ignore("This test ullustrates the problem")
@SuppressWarnings("SpellCheckingInspection")
public class ClientTest {

    public static final int SEQUENCE_LENGTH = 100_000_000;
//    public static final int SEQUENCE_LENGTH = 10_000_000;

    @Test
    public void checkForConcurrentModificationException() throws InterruptedException {
        SuperSimpleGCParser.showGCLogSummaryAtExit();
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<?> future = pool.submit(() -> {
            long time = System.currentTimeMillis();
            Client client = new Client();
            IntStream.range(0, SEQUENCE_LENGTH).forEach(
                    i -> client.checkAlerts()
            );
            time = System.currentTimeMillis() - time;
            System.out.println("checkForConcurrentModificationException() iteration done in " + time + "ms");
        });
        pool.submit(() -> {
            long time = System.currentTimeMillis();
            AlertProvider prov = AlertProvider.getInstance();
            Alert alert = new Alert("fly loose in the server room", AlertLevel.GREEN);
            IntStream.range(0, SEQUENCE_LENGTH).forEach(
                    i -> {
                        prov.addAlert(alert);
                        prov.removeAlert(alert);
                    }
            );
            time = System.currentTimeMillis() - time;
            System.out.println("checkForConcurrentModificationException() modification done in " + time + "ms");
        });
        pool.shutdown();
        try {
            future.get();
        } catch (ExecutionException e) {
            e.getCause().printStackTrace();
            fail("Exception occurred: " + e.getCause());
        }
        boolean awaiting = true;
        while (awaiting) {
            awaiting = !pool.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    @Test
    public void severalAlarms() {
        Alert[] alerts = {
                new Alert("Submarine approaching", AlertLevel.RED),
                new Alert("Grexit", AlertLevel.GREEN),
                new Alert("Italian Cruise Captain", AlertLevel.ORANGE)
        };
        Stream.of(alerts).forEach(
                alert -> AlertProvider.getInstance().addAlert(alert));
        Stream.of(alerts).forEach(
                alert -> AlertProvider.getInstance().removeAlert(alert));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAlertsAreUnmodifiable() {
        AlertProvider.getInstance().getAlerts().clear();
    }
}