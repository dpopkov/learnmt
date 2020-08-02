package learn.mt.mpogr.prodcons;

import java.io.File;

public class MatricesMultiplierConsumer extends Thread {
    private final ThreadSafeQueue queue;
    private final File file;

    public MatricesMultiplierConsumer(ThreadSafeQueue queue, File file) {
        this.queue = queue;
        this.file = file;
    }

    @Override
    public void run() {
        try (MatrixWriter writer = new MatrixWriter(file)) {
            while (!Thread.interrupted()) {
                try {
                    MatricesPair pair = queue.remove();
                    if (pair == null) {
                        System.out.printf(
                                "No more matrices to read. Consumer to file %s terminates.%n", file);
                        break;
                    }
                    float[][] m = multiplyMatrices(pair.matrix1, pair.matrix2);
                    writer.saveMatrix(m);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float[][] multiplyMatrices(float[][] m0, float[][] m1) {
        int size = m0.length;
        float[][] m = new float[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                m[r][c] = multiplyRowByColumn(m0, r, m1, c);
            }
        }
        return m;
    }

    private float multiplyRowByColumn(float[][] m0, int r, float[][] m1, int c) {
        float result = 0;
        for (int i = 0; i < m0[r].length; i++) {
            result += m0[r][i] * m1[i][c];
        }
        return result;
    }
}
