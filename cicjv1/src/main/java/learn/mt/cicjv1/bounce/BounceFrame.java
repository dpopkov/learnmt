package learn.mt.cicjv1.bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    public static final int STEPS = 1_000;
    public static final int DELAY = 3;

    private final BallComponent comp = new BallComponent();

    public BounceFrame() throws HeadlessException {
        setTitle("Bounce");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", e -> addBall());
        addButton(buttonPanel, "Close", e -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void addButton(JPanel buttonPanel, String title, ActionListener listener) {
        JButton button = new JButton(title);
        buttonPanel.add(button);
        button.addActionListener(listener);
    }

    public void addBall() {
        Ball ball = new Ball();
        System.out.println("Adding ball in thread " + Thread.currentThread().getName());
        comp.add(ball);
        try {
            for (int i = 0; i < STEPS; i++) {
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
