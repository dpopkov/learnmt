package learn.mt.mpogr.prodcons;

import java.io.File;
import java.io.FileNotFoundException;

public class MatricesReaderProducer extends Thread {
    private final File file;
    private final ThreadSafeQueue queue;
    private final int matrixSize;

    public MatricesReaderProducer(File file, ThreadSafeQueue queue, int matrixSize) {
        this.file = file;
        this.queue = queue;
        this.matrixSize = matrixSize;
    }

    @Override
    public void run() {
        try (MatrixReader reader = new MatrixReader(file)) {
            boolean reading = true;
            while (reading) {
                float[][] m0 = reader.readMatrix(matrixSize);
                float[][] m1 = reader.readMatrix(matrixSize);
                if (m0 != null && m1 != null) {
                    try {
                        queue.add(new MatricesPair(m0, m1));
                    } catch (InterruptedException e) {
                        reading = false;
                    }
                } else {
                    reading = false;
                }
            }
            queue.terminate();
            System.out.println("No more matrices to read. Producer thread is terminating.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
