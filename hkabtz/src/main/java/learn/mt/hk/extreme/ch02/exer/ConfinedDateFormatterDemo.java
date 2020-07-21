package learn.mt.hk.extreme.ch02.exer;

public class ConfinedDateFormatterDemo {
    public static void main(String[] args) throws InterruptedException {
        ConfinedDateFormatterTester testBase = new ConfinedDateFormatterTester(100,
                200, DateFormatCounted::getNumInstances);
        System.out.println("Warming-up");
        testBase.runTest(new DateFormatterStackConfined());
        System.out.println("---------------------------------");
        testBase.runTest(new DateFormatterThreadConfined());
        testBase.runTest(new DateFormatterStackConfined());
        testBase.runTest(new DateFormatterObjectConfined());
        testBase.runTest(new DateFormatterImmutable());
    }
}
