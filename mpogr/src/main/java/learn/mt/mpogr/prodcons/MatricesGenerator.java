package learn.mt.mpogr.prodcons;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MatricesGenerator {
    private static final String OUTPUT_FILE = "./out/matrices";
    private static final int NUM_MATRIX_PAIRS = 100_000;
    private static final int N = 10;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws Exception {
        File file = new File(OUTPUT_FILE);
        try (MatrixWriter writer = new MatrixWriter(file)) {
            createMatrices(writer);
        }
        System.out.println("Generated and saved to " + OUTPUT_FILE);
    }

    private static void createMatrices(MatrixWriter writer) throws IOException {
        for (int i = 0; i < NUM_MATRIX_PAIRS; i++) {
            float[][] m = createMatrix();
            writer.saveMatrix(m);
        }
    }

    private static float[][] createMatrix() {
        float[][] m = new float[N][];
        for (int r = 0; r < N; r++) {
            m[r] = createRow();
        }
        return m;
    }

    private static float[] createRow() {
        float[] row = new float[N];
        for (int i = 0; i < N; i++) {
            row[i] = RANDOM.nextFloat();
        }
        return row;
    }
}
