Mastering Threads
-----------------

### Exercise 2.1: Thread Pool
Create your own ThreadPool, using:
* ThreadGroup: To shut down all the threads with stop()
* wait/notifyAll
* Pool of worker threads

Must have this functionality:
* Submit a Runnable asynchronously
* Configurable fixed pool size
* Shut down using group.stop()
* Method for viewing run queue length

Solution:
[GroupingThreadPool](p02exer/GroupingThreadPool.java),
[GroupingThreadPoolDemo](p02exer/GroupingThreadPoolDemo.java)

### Exercises 2.2: Priorities
* Construct one thread for each priority
* Wait until they have been started, then start them all off at the same time
* Give them each equal amount of work to do and measure how fast they complete

Solution:
[MeasurePriorities](p02exer/MeasurePriorities.java)