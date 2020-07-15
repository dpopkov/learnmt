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

### Producer-consumers and queues
[LiftOffRunner](src/main/java/learn/mt/mttij/p4cooperation/queues/LiftOffRunner.java),
[TestBlockingQueues](src/main/java/learn/mt/mttij/p4cooperation/queues/TestBlockingQueues.java)

ToastOMatic example:  
[Toast](src/main/java/learn/mt/mttij/p4cooperation/toastomatic/Toast.java),  
[Toaster](src/main/java/learn/mt/mttij/p4cooperation/toastomatic/Toaster.java),
[Butterer](src/main/java/learn/mt/mttij/p4cooperation/toastomatic/Butterer.java),
[Jammer](src/main/java/learn/mt/mttij/p4cooperation/toastomatic/Jammer.java),
[Eater](src/main/java/learn/mt/mttij/p4cooperation/toastomatic/Eater.java),  
[ToastOMatic](src/main/java/learn/mt/mttij/p4cooperation/toastomatic/ToastOMatic.java)

Using pipes:  
[Sender](src/main/java/learn/mt/mttij/p4cooperation/pipes/Sender.java),
[Receiver](src/main/java/learn/mt/mttij/p4cooperation/pipes/Receiver.java),
[PipedIO](src/main/java/learn/mt/mttij/p4cooperation/pipes/PipedIO.java)

5 - Deadlock
------------

Dining Philosophers example:  
[Chopstick](src/main/java/learn/mt/mttij/p5deadlocks/philosophers/Chopstick.java),
[Philosopher](src/main/java/learn/mt/mttij/p5deadlocks/philosophers/Philosopher.java),
[DeadlockingDiningPhilosophers](src/main/java/learn/mt/mttij/p5deadlocks/philosophers/DeadlockingDiningPhilosophers.java),  
[FixedDiningPhilosophers](src/main/java/learn/mt/mttij/p5deadlocks/philosophers/FixedDiningPhilosophers.java)

EX31:
[Ex31Philosopher](src/main/java/learn/mt/mttij/p5deadlocks/philosophers/ex31/Ex31Philosopher.java),
[Ex31DiningPhilosophers](src/main/java/learn/mt/mttij/p5deadlocks/philosophers/ex31/Ex31DiningPhilosophers.java)

6 - New library components
--------------------------

### CountDownLatch  
[TaskPortion](src/main/java/learn/mt/mttij/p6newlib/countdownlatch/TaskPortion.java),
[WaitingTask](src/main/java/learn/mt/mttij/p6newlib/countdownlatch/WaitingTask.java),
[CountDownLatchDemo](src/main/java/learn/mt/mttij/p6newlib/countdownlatch/CountDownLatchDemo.java)

### CyclicBarrier  
[Horse](src/main/java/learn/mt/mttij/p6newlib/cyclicbarrier/Horse.java),
[HorseRace](src/main/java/learn/mt/mttij/p6newlib/cyclicbarrier/HorseRace.java)

### DelayQueue  
[DelayedTask](src/main/java/learn/mt/mttij/p6newlib/delayqueue/DelayedTask.java),
[DelayedTaskConsumer](src/main/java/learn/mt/mttij/p6newlib/delayqueue/DelayedTaskConsumer.java),
[DelayQueueDemo](src/main/java/learn/mt/mttij/p6newlib/delayqueue/DelayQueueDemo.java)

### PriorityBlockingQueue  
[PrioritizedTask](src/main/java/learn/mt/mttij/p6newlib/priorityblockingqueue/PrioritizedTask.java),
[PrioritizedTaskProducer](src/main/java/learn/mt/mttij/p6newlib/priorityblockingqueue/PrioritizedTaskProducer.java),
[PrioritizedTaskConsumer](src/main/java/learn/mt/mttij/p6newlib/priorityblockingqueue/PrioritizedTaskConsumer.java),
[PriorityBlockingQueueDemo](src/main/java/learn/mt/mttij/p6newlib/priorityblockingqueue/PriorityBlockingQueueDemo.java)

### ScheduledThreadPoolExecutor
[GreenHouseScheduler](src/main/java/learn/mt/mttij/p6newlib/scheduledexecutor/GreenHouseScheduler.java)

### Semaphore
[Pool](src/main/java/learn/mt/mttij/p6newlib/semaphore/Pool.java),
[Fat](src/main/java/learn/mt/mttij/p6newlib/semaphore/Fat.java),
[CheckoutTask](src/main/java/learn/mt/mttij/p6newlib/semaphore/CheckoutTask.java),
[SemaphoreDemo](src/main/java/learn/mt/mttij/p6newlib/semaphore/SemaphoreDemo.java)

### Exchanger
[ExchangerProducer](src/main/java/learn/mt/mttij/p6newlib/exchanger/ExchangerProducer.java),
[ExchangerConsumer](src/main/java/learn/mt/mttij/p6newlib/exchanger/ExchangerConsumer.java),
[ExchangerDemo](src/main/java/learn/mt/mttij/p6newlib/exchanger/ExchangerDemo.java)

7 - Simulation
-------------- 

### Bank teller simulation
[Customer](src/main/java/learn/mt/mttij/p7simulation/bankteller/Customer.java),
[CustomerLine](src/main/java/learn/mt/mttij/p7simulation/bankteller/CustomerLine.java),
[CustomerGenerator](src/main/java/learn/mt/mttij/p7simulation/bankteller/CustomerGenerator.java),  
[Teller](src/main/java/learn/mt/mttij/p7simulation/bankteller/Teller.java),
[TellerManager](src/main/java/learn/mt/mttij/p7simulation/bankteller/TellerManager.java),
[BankTellerSimulation](src/main/java/learn/mt/mttij/p7simulation/bankteller/BankTellerSimulation.java)

### Restaurant simulation
[Food](src/main/java/learn/mt/mttij/p7simulation/restaurant/Food.java),
[Course](src/main/java/learn/mt/mttij/p7simulation/restaurant/Course.java),
[Plate](src/main/java/learn/mt/mttij/p7simulation/restaurant/Plate.java),
[Order](src/main/java/learn/mt/mttij/p7simulation/restaurant/Order.java),  
[Customer](src/main/java/learn/mt/mttij/p7simulation/restaurant/Customer.java),
[WaitPerson](src/main/java/learn/mt/mttij/p7simulation/restaurant/WaitPerson.java),
[Chef](src/main/java/learn/mt/mttij/p7simulation/restaurant/Chef.java),  
[Restaurant](src/main/java/learn/mt/mttij/p7simulation/restaurant/Restaurant.java),
[RestaurantWithQueues](src/main/java/learn/mt/mttij/p7simulation/restaurant/RestaurantWithQueues.java)

### Distributing work (car assembling line example)
[Car](src/main/java/learn/mt/mttij/p7simulation/distributing/Car.java),
[CarQueue](src/main/java/learn/mt/mttij/p7simulation/distributing/CarQueue.java),
[ChassisBuilder](src/main/java/learn/mt/mttij/p7simulation/distributing/ChassisBuilder.java),
[Assembler](src/main/java/learn/mt/mttij/p7simulation/distributing/Assembler.java),
[Robot](src/main/java/learn/mt/mttij/p7simulation/distributing/Robot.java),
[RobotPool](src/main/java/learn/mt/mttij/p7simulation/distributing/RobotPool.java),
[DriveTrainRobot](src/main/java/learn/mt/mttij/p7simulation/distributing/DriveTrainRobot.java),
[WheelRobot](src/main/java/learn/mt/mttij/p7simulation/distributing/WheelRobot.java),
[EngineRobot](src/main/java/learn/mt/mttij/p7simulation/distributing/Car.java),
[Reporter](src/main/java/learn/mt/mttij/p7simulation/distributing/Reporter.java),
[CarAssemblingDemo](src/main/java/learn/mt/mttij/p7simulation/distributing/CarAssemblingDemo.java)

8 - Performance tuning
----------------------

### Comparing mutex technologies

Naive comparing:  
[Incrementable](src/main/java/learn/mt/mttij/p8performance/mutex/naive/Incrementable.java),
[SynchronizingIncrementable](src/main/java/learn/mt/mttij/p8performance/mutex/naive/SynchronizingIncrementable.java),
[LockingIncrementable](src/main/java/learn/mt/mttij/p8performance/mutex/naive/LockingIncrementable.java),
[LockingIncrementable](src/main/java/learn/mt/mttij/p8performance/mutex/naive/SimpleMicroBenchmark.java)

Complex comparing:  
[Accumulator](src/main/java/learn/mt/mttij/p8performance/mutex/complex/Accumulator.java),
[BaseAccumulator](src/main/java/learn/mt/mttij/p8performance/mutex/complex/BaseAccumulator.java),
[SynchronizedAccumulator](src/main/java/learn/mt/mttij/p8performance/mutex/complex/SynchronizedAccumulator.java),
[LockAccumulator](src/main/java/learn/mt/mttij/p8performance/mutex/complex/LockAccumulator.java),
[AtomicAccumulator](src/main/java/learn/mt/mttij/p8performance/mutex/complex/AtomicAccumulator.java),
[SynchronizationComparisons](src/main/java/learn/mt/mttij/p8performance/mutex/complex/SynchronizationComparisons.java)

9 - Active objects
------------------