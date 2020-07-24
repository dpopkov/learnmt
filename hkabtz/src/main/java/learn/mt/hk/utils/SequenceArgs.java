package learn.mt.hk.utils;

/**
 * Supplies successive parsed arguments from a sequence of arguments in method <code>main</code>.
 */
public class SequenceArgs {
    private final String[] args;
    private int current = 0;

    public SequenceArgs(String[] args) {
        this.args = args;
    }

    public int nextInt(int defaultValue) {
        int arg = defaultValue;
        if (args.length > current) {
            arg = Integer.parseInt(args[current++]);
        }
        return arg;
    }

    public boolean nextFlag(boolean defaultValue, String name) {
        boolean arg = defaultValue;
        if (args.length > current) {
            arg = name.equals(args[current++]);
        }
        return arg;
    }
}
