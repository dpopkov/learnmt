package learn.mt.mttij.p7simulation.distributing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CarAssemblingDemo {
    public static void main(String[] args) throws InterruptedException {
        CarQueue chassisQueue = new CarQueue();
        CarQueue finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();

        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        exec.execute(new Reporter(finishingQueue));
        exec.execute(new ChassisBuilder(chassisQueue));

        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}
