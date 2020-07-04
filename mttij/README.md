mttij - Multithreading in "Thinking in Java"
============================================

[MindMap](https://coggle.it/diagram/XveP_5G51Z1Ax7oD/t/-)

1 - Basic Threading
-------------------

### Defining tasks
[LiftOff](src/main/java/learn/mt/mttij/p01basic/LiftOff.java),
[MainThread](src/main/java/learn/mt/mttij/p01basic/MainThread.java)

### The Thread class
[BasicThreads](src/main/java/learn/mt/mttij/p01basic/BasicThreads.java),
[MoreBasicThreads](src/main/java/learn/mt/mttij/p01basic/MoreBasicThreads.java),
[Ex01](src/main/java/learn/mt/mttij/p01basic/ex/Ex01.java),
[Ex02](src/main/java/learn/mt/mttij/p01basic/ex/Ex02.java)

### Using Executors
[CachedThreadPool](src/main/java/learn/mt/mttij/p01basic/CachedThreadPool.java),
[FixedThreadPool](src/main/java/learn/mt/mttij/p01basic/FixedThreadPool.java),
[SingleThreadPool](src/main/java/learn/mt/mttij/p01basic/SingleThreadPool.java)

### Producing return values from tasks
[TaskWithResult](src/main/java/learn/mt/mttij/p01basic/TaskWithResult.java),
[CallableDemo](src/main/java/learn/mt/mttij/p01basic/CallableDemo.java),
[Ex05](src/main/java/learn/mt/mttij/p01basic/ex/Ex05.java)

### Sleeping
[SleepingTask](src/main/java/learn/mt/mttij/p01basic/SleepingTask.java),
[Ex06SleepingTask](src/main/java/learn/mt/mttij/p01basic/ex/Ex06SleepingTask.java)

### Priority
[SimplePriorities](src/main/java/learn/mt/mttij/p01basic/SimplePriorities.java)  
[Ex09Priorities](src/main/java/learn/mt/mttij/p01basic/ex/Ex09Priorities.java)

### Daemon threads
[SimpleDaemons](src/main/java/learn/mt/mttij/p01basic/SimpleDaemons.java)  
[DaemonThreadFactory](src/main/java/learn/mt/mttij/p01basic/DaemonThreadFactory.java),
[DaemonFromFactory](src/main/java/learn/mt/mttij/p01basic/DaemonFromFactory.java),
[DaemonThreadPoolExecutor](src/main/java/learn/mt/mttij/p01basic/DaemonThreadPoolExecutor.java),
[Daemons](src/main/java/learn/mt/mttij/p01basic/Daemons.java),
[DaemonsDoNotRunFinally](src/main/java/learn/mt/mttij/p01basic/DaemonsDoNotRunFinally.java)  
[Ex08](src/main/java/learn/mt/mttij/p01basic/ex/Ex08.java)

### Coding variations
[SimpleThread](src/main/java/learn/mt/mttij/p01basic/SimpleThread.java),
[SelfManaged](src/main/java/learn/mt/mttij/p01basic/SelfManaged.java),
[ThreadVariations](src/main/java/learn/mt/mttij/p01basic/ThreadVariations.java),
[Ex10Fibonacci](src/main/java/learn/mt/mttij/p01basic/ex/Ex10Fibonacci.java)

### Joining a thread
[Joining](src/main/java/learn/mt/mttij/p01basic/Joining.java)

### Creating responsive user interfaces
[UnresponsiveUI](src/main/java/learn/mt/mttij/p01basic/UnresponsiveUI.java),
[ResponsiveUI](src/main/java/learn/mt/mttij/p01basic/ResponsiveUI.java)

### Catching exceptions
[ExceptionThread](src/main/java/learn/mt/mttij/p01basic/ExceptionThread.java),
[AttemptToCatchException](src/main/java/learn/mt/mttij/p01basic/AttemptToCatchException.java)

[CaptureUncaughtException](src/main/java/learn/mt/mttij/p01basic/CaptureUncaughtException.java)

2 - Sharing Resources
---------------------

### Improperly accessing resources
[IntGenerator](src/main/java/learn/mt/mttij/p02sharing/IntGenerator.java),
[EvenChecker](src/main/java/learn/mt/mttij/p02sharing/EvenChecker.java),
[EvenGenerator](src/main/java/learn/mt/mttij/p02sharing/EvenGenerator.java)

### Resolving shared resource contention
[SynchronizedEvenGenerator](src/main/java/learn/mt/mttij/p02sharing/SynchronizedEvenGenerator.java),
[Ex11](src/main/java/learn/mt/mttij/p02sharing/ex/Ex11.java)

[MutexEvenGenerator](src/main/java/learn/mt/mttij/p02sharing/MutexEvenGenerator.java)  
[AttemptLocking](src/main/java/learn/mt/mttij/p02sharing/AttemptLocking.java)

### Atomicity and volatility


3 - Terminating tasks
---------------------


4 - Cooperation between tasks
-----------------------------


5 - Deadlock
------------


6 - New library components
--------------------------


7 - Simulation
-------------- 


8 - Performance tuning
----------------------


9 - Active objects
------------------