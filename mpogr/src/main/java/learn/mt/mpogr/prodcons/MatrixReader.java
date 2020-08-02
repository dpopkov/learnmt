package learn.mt.mpogr.prodcons;

import java.io.*;
import java.util.Scanner;

public class MatrixReader implements AutoCloseable {
    private final Scanner scanner;

    public MatrixReader(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
    }

    public synchronized float[][] readMatrix(int size) {
        float[][] m = new float[size][size];
        for (int r = 0; r < size; r++) {
            if (!scanner.hasNext()) {
                return null;
            }
            String line = scanner.nextLine();
            String[] tokens = line.split(", ");
            if (tokens.length != size) {
                throw new IllegalStateException("Number of tokens not equal to size of matrix");
            }
            for (int c = 0; c < size; c++) {
                m[r][c] = Float.parseFloat(tokens[c]);
            }
        }
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // eat empty line
        }
        return m;
    }

    @Override
    public void close() {
        scanner.close();
    }
}
