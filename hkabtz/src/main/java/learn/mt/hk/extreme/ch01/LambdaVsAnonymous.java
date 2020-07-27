package learn.mt.hk.extreme.ch01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.RandomAccess;

@SuppressWarnings("unused")
public class LambdaVsAnonymous {
    public static void main(String[] args) {
        LambdaVsAnonymous object = new LambdaVsAnonymous();
        showContextsDifference(object);
        showRunnableRecreation();
        showLambdaRecreation();
        anonymousCanImplementManyMethods();
        anonymousCanExtendClasses();
        lambdasCanImplementInterfacesWithoutMethods();
        anonymousCanContainState();
    }

    private static void showContextsDifference(LambdaVsAnonymous object) {
        System.out.println("Lambda and Anonymous Inner class have different contexts");
        System.out.println("--------------------------------------------------------");
        object.fooLambda();
        object.fooRunnable();
        System.out.println();
    }

    @SuppressWarnings("Convert2Lambda")
    private static void showRunnableRecreation() {
        System.out.println("In loop Runnable is created every time, but Lambda is used the same.");
        System.out.println("--------------------------------------------------------------------");
        for (int i = 0; i < 3; i++) {
            Runnable r1 = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Does nothing");
                }
            };
            Runnable r2 = () -> System.out.println("Does nothing");
            System.out.println("Runnable: " + r1);
            System.out.println("Lambda  : " + r2);
        }
        System.out.println();
    }

    @SuppressWarnings("Convert2Lambda")
    private static void showLambdaRecreation() {
        System.out.println("In loop Lambda is recreated if referenced some loop created variable.");
        System.out.println("--------------------------------------------------------------------");
        for (int i = 0; i < 3; i++) {
            Runnable r1 = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Does nothing");
                }
            };
            Runnable r2 = () -> System.out.println("Does nothing, but has reference to " + r1);
            System.out.println("Runnable: " + r1);
            System.out.println("Lambda  : " + r2);
        }
        System.out.println();
    }

    private static void anonymousCanImplementManyMethods() {
        System.out.println("Anonymous classes can implement more than one method, but lambda only 1");
        System.out.println();
    }

    private static void anonymousCanExtendClasses() {
        System.out.println("Anonymous classes can extends other classes, "
                + "but lambda only can implement Functional interface");
        System.out.println();
    }

    private static void lambdasCanImplementInterfacesWithoutMethods() {
        Runnable r3 = (Runnable & Serializable & Cloneable & RandomAccess) () -> System.out.println("Hello");
        System.out.println(Arrays.toString(r3.getClass().getInterfaces()));
        System.out.println();
    }

    private static void anonymousCanContainState() {
        ArrayList<String> list = new ArrayList<>() {
            private int number = 42;

            @Override
            public boolean add(String s) {
                number++;
                return super.add(s);
            }
        };
        System.out.println(list);
    }

    private void fooLambda() {
        System.out.println("In lambda 'this' refers to object that contains this method");
        Runnable r = () -> System.out.printf("this is %s, hashcode=%x%n", this, System.identityHashCode(this));
        r.run();
    }

    private void fooRunnable() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("In anonymous inner class 'this' refers to object Runnable");
                System.out.printf("this is %s, hashcode=%x%n", this, System.identityHashCode(this));
            }
        };
        r.run();
    }
}
