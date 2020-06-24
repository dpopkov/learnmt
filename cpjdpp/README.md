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
