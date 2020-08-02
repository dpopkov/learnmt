package learn.mt.mpogr.prodcons;

import java.io.*;
import java.util.Locale;

public class MatrixWriter implements AutoCloseable {
    private final BufferedWriter writer;

    public MatrixWriter(File file) throws IOException {
        writer = new BufferedWriter(new FileWriter(file));
    }

    public synchronized void saveMatrix(float[][] matrix) throws IOException {
        Locale locale = Locale.US;
        for (float[] row : matrix) {
            int lastCol = row.length - 1;
            for (int c = 0; c < lastCol; c++) {
                writer.write(String.format(locale, "%.2f, ", row[c]));
            }
            writer.write(String.format(locale, "%.2f%n", row[lastCol]));
        }
        writer.write(System.lineSeparator());
    }

    @Override
    public synchronized void close() throws Exception {
        writer.flush();
        writer.close();
    }
}
