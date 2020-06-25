package learn.mt.cpjdpp.immutability;

public class Fraction {
    private final long numerator;
    private final long denominator;

    public Fraction(long num, long den) {
        boolean sameSign = (num >= 0) == (den >= 0);
        long n = (num >= 0) ? num : -num;
        long d = (den >= 0) ? den : -den;
        long g = gcd(n, d);
        numerator = (sameSign) ? n / g : -n / g;
        denominator = d / g;
    }

    /** Computes greatest common divisor. */
    private static long gcd(long n, long d) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Fraction plus(Fraction f) {
        return new Fraction(numerator * f.denominator + f.numerator * denominator,
                denominator * f.denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fraction f = (Fraction) o;
        return numerator * f.denominator == denominator * f.numerator;
    }

    @Override
    public int hashCode() {
        return (int) (numerator ^ denominator);
    }
}
