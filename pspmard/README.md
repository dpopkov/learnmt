pspmard

# Pluralsight. Applying-Concurrency-and-Multi-threading-to-Common-Java-Patterns.

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

