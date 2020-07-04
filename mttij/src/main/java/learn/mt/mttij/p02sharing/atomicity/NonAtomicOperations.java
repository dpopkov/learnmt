package learn.mt.mttij.p02sharing.atomicity;

public class NonAtomicOperations {
    int i;

    void f1() {
        i++;
    }

    void f2() {
        i += 3;
    }

    public static void main(String[] args) {
        System.out.println("View byte code!");
    }
}
