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
[AtomicityTest](src/main/java/learn/mt/mttij/p02sharing/atomicity/AtomicityTest.java)  
[SerialNumberGenerator](src/main/java/learn/mt/mttij/p02sharing/atomicity/SerialNumberGenerator.java),
[SerialNumberChecker](src/main/java/learn/mt/mttij/p02sharing/atomicity/SerialNumberChecker.java)

### Atomic classes
[AtomicIntegerTest](src/main/java/learn/mt/mttij/p02sharing/atomicity/AtomicIntegerTest.java),
[AtomicEvenGenerator](src/main/java/learn/mt/mttij/p02sharing/atomicity/AtomicEvenGenerator.java)  
[Ex14TimerScaling](src/main/java/learn/mt/mttij/p02sharing/atomicity/ex/Ex14TimerScaling.java)

### Critical sections
These examples do not work properly:  
[Pair](src/main/java/learn/mt/mttij/p02sharing/critical/Pair.java),
[PairManager](src/main/java/learn/mt/mttij/p02sharing/critical/PairManager.java),
[PairManager1](src/main/java/learn/mt/mttij/p02sharing/critical/PairManager1.java),
[PairManager2](src/main/java/learn/mt/mttij/p02sharing/critical/PairManager2.java),  
[PairManipulator](src/main/java/learn/mt/mttij/p02sharing/critical/PairManipulator.java),
[PairChecker](src/main/java/learn/mt/mttij/p02sharing/critical/PairChecker.java),
[CriticalSection](src/main/java/learn/mt/mttij/p02sharing/critical/CriticalSection.java)

[ExplicitPairManager1](src/main/java/learn/mt/mttij/p02sharing/critical/ExplicitPairManager1.java),
[ExplicitPairManager2](src/main/java/learn/mt/mttij/p02sharing/critical/ExplicitPairManager2.java),
[ExplicitCriticalSection](src/main/java/learn/mt/mttij/p02sharing/critical/ExplicitCriticalSection.java)

### Synchronization on other objects
[SyncObject](src/main/java/learn/mt/mttij/p02sharing/critical/SyncObject.java),
[Ex15](src/main/java/learn/mt/mttij/p02sharing/critical/ex/Ex15.java),
[Ex16](src/main/java/learn/mt/mttij/p02sharing/critical/ex/Ex16.java)

### Thread local storage
[ThreadLocalVariableHolder](src/main/java/learn/mt/mttij/p02sharing/ThreadLocalVariableHolder.java)


3 - Terminating tasks
---------------------

### The ornamental garden example
[Count](src/main/java/learn/mt/mttij/p03terminating/garden/Count.java),
[Entrance](src/main/java/learn/mt/mttij/p03terminating/garden/Entrance.java),
[OrnamentalGarden](src/main/java/learn/mt/mttij/p03terminating/garden/OrnamentalGarden.java)

### Interruption
[SleepBlocked](src/main/java/learn/mt/mttij/p03terminating/interruption/SleepBlocked.java),
[IOBlocked](src/main/java/learn/mt/mttij/p03terminating/interruption/IOBlocked.java),
[SynchronizedBlocked](src/main/java/learn/mt/mttij/p03terminating/interruption/SynchronizedBlocked.java),  
[Interrupting](src/main/java/learn/mt/mttij/p03terminating/interruption/Interrupting.java)  
[CloseResource](src/main/java/learn/mt/mttij/p03terminating/interruption/CloseResource.java)  
[Ex18Interrupting](src/main/java/learn/mt/mttij/p03terminating/interruption/ex/Ex18Interrupting.java)  
Ex19:
[Count](src/main/java/learn/mt/mttij/p03terminating/interruption/ex/ex19/Count.java), 
[Entrance](src/main/java/learn/mt/mttij/p03terminating/interruption/ex/ex19/Entrance.java), 
[OrnamentalGarden](src/main/java/learn/mt/mttij/p03terminating/interruption/ex/ex19/OrnamentalGarden.java)  
Ex20:
[LiftOff](src/main/java/learn/mt/mttij/p03terminating/interruption/ex/ex20/LiftOff.java),
[CachedThreadPool](src/main/java/learn/mt/mttij/p03terminating/interruption/ex/ex20/CachedThreadPool.java)  

#### Blocked by a mutex
[MultiLock](src/main/java/learn/mt/mttij/p03terminating/interruption/MultiLock.java)

[BlockedMutex](src/main/java/learn/mt/mttij/p03terminating/blocked/BlockedMutex.java),
[BlockedTask](src/main/java/learn/mt/mttij/p03terminating/blocked/BlockedTask.java),
[InterruptingBlockedTask](src/main/java/learn/mt/mttij/p03terminating/blocked/InterruptingBlockedTask.java)

### Checking for an interrupt
[NeedsCleanup](src/main/java/learn/mt/mttij/p03terminating/checking/NeedsCleanup.java),
[BlockedAndInterrupted](src/main/java/learn/mt/mttij/p03terminating/checking/BlockedAndInterrupted.java),
[InterruptingIdiom](src/main/java/learn/mt/mttij/p03terminating/checking/InterruptingIdiom.java)

4 - Cooperation between tasks
-----------------------------

### Waxomatic example
[Car](src/main/java/learn/mt/mttij/p4cooperation/waxomatic/Car.java),
[WaxOn](src/main/java/learn/mt/mttij/p4cooperation/waxomatic/WaxOn.java),
[WaxOff](src/main/java/learn/mt/mttij/p4cooperation/waxomatic/WaxOff.java),
[WaxOMatic](src/main/java/learn/mt/mttij/p4cooperation/waxomatic/WaxOMatic.java)

[Ex21](src/main/java/learn/mt/mttij/p4cooperation/ex/Ex21.java),
[Ex22v1](src/main/java/learn/mt/mttij/p4cooperation/ex/Ex22v1.java),
[Ex22v2](src/main/java/learn/mt/mttij/p4cooperation/ex/Ex22v2.java)

#### notify() vs notifyAll()
[Blocker](src/main/java/learn/mt/mttij/p4cooperation/notifyall/Blocker.java),
[Task](src/main/java/learn/mt/mttij/p4cooperation/notifyall/Task.java),
[Task2](src/main/java/learn/mt/mttij/p4cooperation/notifyall/Task2.java),
[NotifyVsNotifyAll](src/main/java/learn/mt/mttij/p4cooperation/notifyall/NotifyVsNotifyAll.java)

### Producers and consumers
[Meal](src/main/java/learn/mt/mttij/p4cooperation/restaurant/Meal.java),
[Chef](src/main/java/learn/mt/mttij/p4cooperation/restaurant/Chef.java),
[WaitPerson](src/main/java/learn/mt/mttij/p4cooperation/restaurant/WaitPerson.java),
[Restaurant](src/main/java/learn/mt/mttij/p4cooperation/restaurant/Restaurant.java)

Ex24:
[Ex24Queue](src/main/java/learn/mt/mttij/p4cooperation/ex/Ex24Queue.java),
[Ex24Producer](src/main/java/learn/mt/mttij/p4cooperation/ex/Ex24Producer.java),
[Ex24Consumer](src/main/java/learn/mt/mttij/p4cooperation/ex/Ex24Consumer.java),
[Ex24Main](src/main/java/learn/mt/mttij/p4cooperation/ex/Ex24Main.java)

Ex26:
[Chef](src/main/java/learn/mt/mttij/p4cooperation/ex/ex26/Chef.java),
[WaitPerson](src/main/java/learn/mt/mttij/p4cooperation/ex/ex26/WaitPerson.java),
[BusBoy](src/main/java/learn/mt/mttij/p4cooperation/ex/ex26/BusBoy.java),
[Restaurant](src/main/java/learn/mt/mttij/p4cooperation/ex/ex26/Restaurant.java)

#### Explicit Lock and Condition objects
[Car](src/main/java/learn/mt/mttij/p4cooperation/waxomatic2/Car.java),
[WaxOn](src/main/java/learn/mt/mttij/p4cooperation/waxomatic2/WaxOn.java),
[WaxOff](src/main/java/learn/mt/mttij/p4cooperation/waxomatic2/WaxOff.java),
[WaxOMatic2](src/main/java/learn/mt/mttij/p4cooperation/waxomatic2/WaxOMatic2.java)

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