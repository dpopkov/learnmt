package learn.mt.mttij.p02sharing.critical;

public class Pair {
    private int x;
    private int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    public void checkState() {
        if (x != y) {
            throw new PairValuesNotEqualsException();
        }
    }

    public class PairValuesNotEqualsException extends RuntimeException {
        public PairValuesNotEqualsException() {
            super("Pair values not equal: " + Pair.this);
        }
    }
}
