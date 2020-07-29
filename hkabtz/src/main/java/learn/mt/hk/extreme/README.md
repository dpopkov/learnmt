Extreme Java - Concurrency Performance
--------------------------------------

### Chapter 1
1 - About  
2 - Exercises  
3 - History  
4 - Benefits of threads  
5 - Risks of threads  
6 - Threads are everywhere  
7 - Short Java 7 and 8 primer:
[LambdaVsAnonymous](ch01/LambdaVsAnonymous.java), 
[PassengerAndTrainDemo](ch01/PassengerAndTrainDemo.java),
[Exercise 1 - JCIP Annotations](ch01/exer)

### Chapter 2 - Thread Safety
11 - Stack vs Heap memory  
12 - Synchronization, Latent defects  
13 - Atomicity  
14 - Visibility:
[WaitingForMeaningOfLife](ch02/WaitingForMeaningOfLife.java)    
15 - Confinement  
16 - Immutability:
[Immutable1](ch02/Immutable1.java)  
17 - Designing a thread-safe class  
18 - Exercises: [Confined DateFormats](ch02/exer),

### Chapter 3 - Building Blocks
24 - Synchronized Collections  
25 - Concurrent Collections  
26 - Livelock with ConcurrentHashMap.computeIfAbsent()  
27 - CopyOnWrite collections  
28 - BlockingQueue and Producer/Consumer  

#### 3.4 - Synchronizers
A synchronizer is any object that coordinates the control flow of threads based on its state.

29 - Semaphore  
30 - CountDownLatch  
31 - Phaser  
32 - Exercises:  
[ConcurrentModificationException](ch03/exer31),
[ConcurrentModificationException - solution](ch03/exer31solution),  
[PriorityBlockingQueue](ch03/exer32), 
[PriorityBlockingQueue - solution](ch03/exer32solution) 
