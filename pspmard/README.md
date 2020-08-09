pspmard

# Pluralsight. Applying Concurrency and Multi-threading to Common Java Patterns.

### False Sharing
[FalseSharingDemo](src/main/java/learn/mt/pspmard/acmtcjp/falsesharing/FalseSharingDemo.java)

### Thread Safe Singleton Pattern
First implementation (not thread safe):
[SingletonNaive](src/main/java/learn/mt/pspmard/acmtcjp/singleton/SingletonNaive.java)

Second implementation (using simple synchronization):
[SingletonSync](src/main/java/learn/mt/pspmard/acmtcjp/singleton/SingletonSync.java)

The Double Check Locking Singleton Pattern (Buggy):
[SingletonDclBuggy](src/main/java/learn/mt/pspmard/acmtcjp/singleton/SingletonDclBuggy.java)

The Double Check Locking Singleton Pattern (Fixed with volatile):
[SingletonDclFixed](src/main/java/learn/mt/pspmard/acmtcjp/singleton/SingletonDclFixed.java)

The enum Singleton is the simplest and recommended solution:
[SingletonEnum](src/main/java/learn/mt/pspmard/acmtcjp/singleton/SingletonEnum.java)

#### Example of synchronized write and read:
[LongWrapper](src/main/java/learn/mt/pspmard/acmtcjp/racestudy/LongWrapper.java),
[RaceConditionChecker](src/main/java/learn/mt/pspmard/acmtcjp/racestudy/RaceConditionChecker.java)

#### Example of unexpected lock release and fixing with creation of an explicit lock:
[WorkerIncorrect](src/main/java/learn/mt/pspmard/acmtcjp/lockmess/WorkerIncorrect.java),
[WorkerFixed](src/main/java/learn/mt/pspmard/acmtcjp/lockmess/WorkerFixed.java)

## How to Write Correct Concurrent Code

#### 1 - Check for race conditions
- They occur on fields (not local variables/parameters)
- 2 threads are reading/writing a given field
#### 2 - Check of the Happens-Before link
- Are the read/write operations volatile?
- Are they synchronized?
- If not, there is a probable bug
#### 3 - Synchronized or volatile?
- Synchronized == atomicity
- Volatile == visibility


# Pluralsight. Advanced Java Concurrent Patterns.

### Producer Consumer
[ProducerConsumerWithLocks](src/main/java/learn/mt/pspmard/ajcp/locks/ProducerConsumerWithLocks.java)

Consumer that uses timeout and does not block when Producer fails:
[ProducerConsumerWithTimeOut](src/main/java/learn/mt/pspmard/ajcp/locks/ProducerConsumerWithTimeOut.java)

Example of using Read Write Locks:
[CacheWithReadWriteLock](src/main/java/learn/mt/pspmard/ajcp/locks/CacheWithReadWriteLock.java)

### Controlling-Concurrent-Applications-Using-Barriers-and-Latches

Example of CyclicBarrier usage:
[BarrierInAction](src/main/java/learn/mt/pspmard/ajcp/barriers/BarrierInAction.java)
