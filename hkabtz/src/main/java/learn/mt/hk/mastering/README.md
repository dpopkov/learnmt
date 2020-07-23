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

My Solution:
[PoolThread](p02exer/PoolThread.java),
[GroupingThreadPool](p02exer/GroupingThreadPool.java),
[GroupingThreadPoolDemo](p02exer/GroupingThreadPoolDemo.java)