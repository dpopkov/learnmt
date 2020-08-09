package learn.mt.pspmard.ajcp.barriers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class BarrierInAction {
    public static void main(String[] args) {
        class Friend implements Callable<String> {
            private final int id;
            private final CyclicBarrier barrier;

            Friend(int id, CyclicBarrier barrier) {
                this.id = id;
                this.barrier = barrier;
            }

            @Override
            public String call() throws Exception {
                Random rand = new Random();
                Thread.sleep(rand.nextInt(10) * 350 + 100);
                System.out.println(id + ": I just arrived, waiting for the others...");
                int arrivalIndex = barrier.await();
                System.out.println(id + ": arrivalIndex = " + arrivalIndex);
                System.out.println(id + ": Let's go to the cinema");
                return id + ":Ok";
            }
        }
        final int numFriends = 4;
        CyclicBarrier barrier = new CyclicBarrier(numFriends,
                () -> System.out.println("Barrier Opening"));
        ExecutorService exec = Executors.newFixedThreadPool(numFriends);
        List<Future<String>> futures = new ArrayList<>();
        try {
            for (int i = 0; i < numFriends; i++) {
                Friend friend = new Friend(i, barrier);
                Future<String> future = exec.submit(friend);
                futures.add(future);
            }
            futures.forEach(
                    future -> {
                        try {
                            String result = future.get();
                            System.out.println(result);
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } finally {
            exec.shutdown();
        }
    }


}
