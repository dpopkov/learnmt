package learn.mt.mpogr.recoloring;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReColoring {
    private static final String SOURCE_FILE = "./resources/image.jpg";
    private static final String DESTINATION_FILE = "./out/image.jpg";

    public static void main(String[] args) throws IOException {
        File source = new File(SOURCE_FILE);
        BufferedImage originalImage = ImageIO.read(source);
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(),
                originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        long time = System.currentTimeMillis();
//        recolorSingleThreaded(originalImage, resultImage);
        recolorMultiThreaded(originalImage, resultImage, 6);
        time = System.currentTimeMillis() - time;
        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);
        System.out.println("Recolored " + time + "ms");
    }

    private static void recolorSingleThreaded(BufferedImage original, BufferedImage result) {
        recolorImage(original, result, 0, 0, original.getWidth(), original.getHeight());
    }

    private static void recolorMultiThreaded(BufferedImage original, BufferedImage result,
                                             int numberOfThreads) {
        final int left = 0;
        int top = 0;
        final int width = original.getWidth();
        int height = original.getHeight() / numberOfThreads;
        int lastHeight = original.getHeight() - (numberOfThreads - 1) * height;
        List<Thread> threads = new ArrayList<>();
        Thread t;
        for (int i = 0; i < numberOfThreads - 1; i++) {
            t = new Thread(new RecoloringTask(original, result, left, top, width, height));
            threads.add(t);
            top += height;
        }
        t = new Thread(new RecoloringTask(original, result, left, top, width, lastHeight));
        threads.add(t);
        threads.forEach(Thread::start);
        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class RecoloringTask implements Runnable {
        private final BufferedImage original;
        private final BufferedImage result;
        private final int left;
        private final int top;
        private final int width;
        private final int height;

        public RecoloringTask(BufferedImage original, BufferedImage result,
                              int left, int top, int width, int height) {
            this.original = original;
            this.result = result;
            this.left = left;
            this.top = top;
            this.width = width;
            this.height = height;
        }

        @Override
        public void run() {
            recolorImage(original, result, left, top, width, height);
        }
    }

    private static void recolorImage(BufferedImage original, BufferedImage result,
                                     int left, int top, int width, int height) {
        int resultWidth = result.getWidth();
        int rightCorner = left + Math.min(width, resultWidth);
        int resultHeight = result.getHeight();
        int bottomCorner = top + Math.min(height, resultHeight);
        for (int x = left; x < rightCorner; x++) {
            for (int y = top; y < bottomCorner; y++) {
                recolorPixel(original, result, x, y);
            }
        }
    }

    public static void recolorPixel(BufferedImage original, BufferedImage result, int x, int y) {
        int rgb = original.getRGB(x, y);
        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue = getBlue(rgb);
        int newRed;
        int newGreen;
        int newBlue;
        if (isShadedGray(red, green, blue, 30)) {
            newRed = Math.min(255,  red + 10);
            newGreen = Math.max(0, green - 80);
            newBlue = Math.max(0, blue - 20);
        } else {
            newRed = red;
            newGreen = green;
            newBlue = blue;
        }
        int newRGB = createRgbFromColors(newRed, newGreen, newBlue);
        setRGB(result, x, y, newRGB);
    }

    public static void setRGB(BufferedImage image, int x, int y, int rgb) {
        image.getRaster().setDataElements(x, y, image.getColorModel().getDataElements(rgb, null));
    }

    public static boolean isShadedGray(int red, int green, int blue, int diff) {
        return Math.abs(red - green) < diff
                && Math.abs(red - blue) < diff
                && Math.abs(green - blue) < diff;
    }

    public static int createRgbFromColors(int red, int green, int blue) {
        int rgb = 0;
        rgb |= red << 16;
        rgb |= green << 8;
        rgb |= blue;
        rgb |= 0xFF000000;
        return rgb;
    }

    public static int getRed(int rgb) {
        return (rgb & 0x00FF0000) >> 16;
    }

    public static int getGreen(int rgb) {
        return (rgb & 0x0000FF00) >> 8;
    }

    public static int getBlue(int rgb) {
        return rgb & 0x000000FF;
    }
}
