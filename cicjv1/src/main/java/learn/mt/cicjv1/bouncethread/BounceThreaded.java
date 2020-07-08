package learn.mt.cicjv1.bouncethread;

import javax.swing.*;
import java.awt.*;

/** Shows an animated bouncing ball. */
public class BounceThreaded {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceThreadedFrame();
            frame.setVisible(true);
        });
    }
}
