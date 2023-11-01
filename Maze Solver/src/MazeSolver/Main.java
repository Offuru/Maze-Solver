package MazeSolver;

import MazeSolver.listeners.FrameListener;

import javax.swing.*;

public class Main {

    static JFrame frame;
    static Panel panel;

    public static void InitUI() {
        frame = new JFrame("GraphBuilder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel = new Panel("labyrinth.txt"));
        frame.setSize(800, 800);
        frame.addComponentListener(new FrameListener(frame, panel));
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InitUI();
            }
        });

    }

}
