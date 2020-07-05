package learn.mt.mttij.p02sharing.atomicity.ex;

import java.util.Timer;
import java.util.TimerTask;

public class Ex14TimerScaling {
    public static void main(String[] args) {
        int delay = 100;
        final int step = 100;
        Timer timer = new Timer();
        final int numTasks = 100;
        for (int i = 0; i < numTasks; i++) {
            timer.schedule(new TaskWithId(i), delay);
            delay += step;
        }
        System.out.println("All timers scheduled");
    }

    private static class TaskWithId extends TimerTask {
        private final int id;

        public TaskWithId(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Timeout " + id);
        }
    }
}
