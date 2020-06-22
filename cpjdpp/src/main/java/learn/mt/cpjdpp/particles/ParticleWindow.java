package learn.mt.cpjdpp.particles;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParticleWindow extends Application {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final long DELAY = 100L;
    public static final long TIME_LIMIT = 60_000L;

    private Thread[] threads;
    private Particle[] particles;

    @Override
    public void init() {
        int n = 10;
        particles = new Particle[n];
        for (int i = 0; i < n; i++) {
            particles[i] = new Particle(WIDTH / 2, HEIGHT / 2);
        }

        threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            threads[i] = makeThread(particles[i], "#" + i);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        ParticlePane particlePane = new ParticlePane(particles);

        Scene scene = new Scene(particlePane, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle(ParticleWindow.class.getSimpleName());
        primaryStage.show();
        startThreads();
    }

    @Override
    public synchronized void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        threads = null;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private synchronized void startThreads() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private Thread makeThread(Particle p, String id) {
        Runnable runnable = new Runnable() {
            private long count;

            @SuppressWarnings("BusyWait")
            @Override
            public void run() {
                try {
                    while (count < TIME_LIMIT) {
                        p.move();
                        p.draw();
                        Thread.sleep(DELAY);
                        count += DELAY;
                    }
                    System.out.println("Thread " + Thread.currentThread().getName()
                            + " reached limit and finishes");
                } catch (InterruptedException e) {
                    System.out.println("Thread " + Thread.currentThread().getName()
                            + " is interrupted and finishes");
                }
            }
        };
        return new Thread(runnable, id);
    }
}
