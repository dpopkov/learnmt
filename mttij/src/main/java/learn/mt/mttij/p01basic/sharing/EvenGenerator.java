package learn.mt.mttij.p01basic.sharing;

public class EvenGenerator extends IntGenerator {
    private int currentEvenValue;

    /*
    It’s possible for one task to call next( ) after another task has performed the first increment
    of currentEvenValue but not the second (at the place in the code commented "Danger
    point here!"). This puts the value into an "incorrect" state.
     */
    @Override
    public int next() {
        currentEvenValue++;      // Danger point here!
        currentEvenValue++;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator(), 10);
    }
}
