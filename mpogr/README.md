mpogr
-----

HTTP server for measuring throughput using Apache JMeter :
[ThroughputHttpServer](src/main/java/learn/mt/mpogr/httpserver/ThroughputHttpServer.java),
[WordCountHandler](src/main/java/learn/mt/mpogr/httpserver/WordCountHandler.java)

Business Metrics Capturing - example of how to use volatile:
[Metrics](src/main/java/learn/mt/mpogr/race/atomic/Metrics.java),
[MetricsPrinter](src/main/java/learn/mt/mpogr/race/atomic/MetricsPrinter.java),
[BusinessLogic](src/main/java/learn/mt/mpogr/race/atomic/BusinessLogic.java)

Deadlock Simulation:
[Intersection](src/main/java/learn/mt/mpogr/deadlock/Intersection.java),
[Train](src/main/java/learn/mt/mpogr/deadlock/Train.java)

Using ReentrantLock in a UI JavaFX application:
[PricesContainer](src/main/java/learn/mt/mpogr/reentrantlock/PricesContainer.java),
[PricesUpdater](src/main/java/learn/mt/mpogr/reentrantlock/PricesUpdater.java),
[MainPricesUI](src/main/java/learn/mt/mpogr/reentrantlock/MainPricesUI.java)

Using ReentrantReadWriteLock in a simple inventory db:
[InventoryDb](src/main/java/learn/mt/mpogr/readwrite/InventoryDb.java),
[InventoryDbLocking](src/main/java/learn/mt/mpogr/readwrite/InventoryDbLocking.java),
[InventoryDbReadWriteLocking](src/main/java/learn/mt/mpogr/readwrite/InventoryDbReadWriteLocking.java),
[InventoryDbRunner](src/main/java/learn/mt/mpogr/readwrite/InventoryDbRunner.java),
[MainReadWriteLockingComparison](src/main/java/learn/mt/mpogr/readwrite/MainReadWriteLockingComparison.java)

Implementing Thread Safe Queue in a Producer-Consumer Example and applying back-pressure limiting
size of the queue:
[MatricesPair](src/main/java/learn/mt/mpogr/prodcons/MatricesPair.java),
[MatricesGenerator](src/main/java/learn/mt/mpogr/prodcons/MatricesGenerator.java),
[ThreadSafeQueue](src/main/java/learn/mt/mpogr/prodcons/ThreadSafeQueue.java),
[MatrixWriter](src/main/java/learn/mt/mpogr/prodcons/MatrixWriter.java),
[MatrixReader](src/main/java/learn/mt/mpogr/prodcons/MatrixReader.java),
[MatricesReaderProducer](src/main/java/learn/mt/mpogr/prodcons/MatricesReaderProducer.java),
[MatricesMultiplierConsumer](src/main/java/learn/mt/mpogr/prodcons/MatricesMultiplierConsumer.java),
[MainProducerConsumer](src/main/java/learn/mt/mpogr/prodcons/MainProducerConsumer.java)
