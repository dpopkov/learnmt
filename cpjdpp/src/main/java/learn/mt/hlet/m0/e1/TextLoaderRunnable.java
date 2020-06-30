package learn.mt.hlet.m0.e1;

import learn.mt.hlet.m0.e0.ITextLoader;

public class TextLoaderRunnable implements Runnable {
    private final ITextLoader textLoader;

    public TextLoaderRunnable(ITextLoader textLoader) {
        this.textLoader = textLoader;
    }

    @Override
    public void run() {
        System.out.println(textLoader.loadText());
    }
}
