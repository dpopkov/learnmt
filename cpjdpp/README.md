cpjdpp - Concurrent Programming in Java: Design Principles and Patterns, 2nd ed
-------------------------------------------------------------------------------

Если проект не модульный то может запускаться через `mvn javafx:run`.  
Имя main класса задается в pom.xml в разделе конфигурации javafx-maven-plugin.  
 

Chapter 1 - Concurrent Object-Oriented Programming
--------------------------------------------------
 
### 1.1 Using Concurrency Constructs

### 1.2 Objects and Concurrency

#### 1.2.4 Object Models and Mappings

[WaterTank](src/main/java/learn/mt/cpjdpp/watertank/WaterTank.java)  

### 1.4 Before/After Patterns

#### 1.4.2 Adapters

[AdaptedTank](src/main/java/learn/mt/cpjdpp/watertank/AdaptedTank.java) - not really an 'Adapter'.

#### 1.4.3 Subclassing

[SubclassedTank](src/main/java/learn/mt/cpjdpp/watertank/SubclassedTank.java)

#### 1.4.3.1 Template methods

[AbstractTank](src/main/java/learn/mt/cpjdpp/watertank/AbstractTank.java)

#### 1.4.4 Method Adapters

[TankWithMethodAdapter](src/main/java/learn/mt/cpjdpp/watertank/TankWithMethodAdapter.java)


Chapter 2 - Exclusion
---------------------

In a safe system, every object protects itself from integrity violations. 
This sometimes requires the cooperation of other objects and their methods.

Exclusion techniques preserve object invariants and avoid effects that would result from acting 
upon even momentarily inconsistent state representations. Programming techniques and design patterns
achieve exclusion by preventing multiple threads from concurrently modifying or acting upon object 
representations. All approaches rely on one or more of three basic strategies:

* Eliminating the need for some or all exclusion control by ensuring that methods never modify an 
object's representation, so that the object cannot enter inconsistent states.

* Dynamically ensuring that only one thread at a time can access object state, 
by protecting objects with locks and related constructs.

* Structurally ensuring that only one thread (or only one thread at a time) can 
ever use a given object, by hiding or restricting access to it.

### 2.1 Immutability

#### 2.1.1 Applications

2.1.1.1 Abstract Data Types (ADTs):
[Fraction](src/main/java/learn/mt/cpjdpp/immutability/Fraction.java) 

### 2.2 Synchronization

#### 2.2.2 Fully Synchronized Objects

[ExpandableArray](src/main/java/learn/mt/cpjdpp/synch/ExpandableArray.java)

### 2.3 Traversal

2.2.3.1 Synchronized aggregate operations:
[ExpandableArrayWidthConsumer](src/main/java/learn/mt/cpjdpp/synch/ExpandableArrayWidthConsumer.java)

2.2.3.3 Versioned iterators:
[ExpandableArrayWithIterator](src/main/java/learn/mt/cpjdpp/synch/ExpandableArrayWithIterator.java)

#### 2.2.4 Statics and Singletons

[LazySingletonCounter](src/main/java/learn/mt/cpjdpp/synch/LazySingletonCounter.java)

Unless initialization is both very expensive and rarely needed, it is usually preferable 
to take the simpler approach of declaring a singleton as a static final field.

[EagerSingletonCounter](src/main/java/learn/mt/cpjdpp/synch/EagerSingletonCounter.java)

#### 2.2.5 Deadlock

[Cell](src/main/java/learn/mt/cpjdpp/synch/deadlocks/Cell.java),
[TryToCreateDeadlock](src/main/java/learn/mt/cpjdpp/synch/deadlocks/TryToCreateDeadlock.java).

#### 2.2.6 Resource Ordering

[CellWithResourceOrdering](src/main/java/learn/mt/cpjdpp/synch/deadlocks/CellWithResourceOrdering.java),
[TryToCreateDeadlockOnOrdered](src/main/java/learn/mt/cpjdpp/synch/deadlocks/TryToCreateDeadlockOnOrdered.java).
