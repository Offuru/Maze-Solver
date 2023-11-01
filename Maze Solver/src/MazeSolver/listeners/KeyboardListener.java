package MazeSolver.listeners;

import java.awt.event.KeyEvent;

import MazeSolver.Panel;

public class KeyboardListener implements java.awt.event.KeyListener {

    Panel panel;

    public KeyboardListener(Panel panel) {
        this.panel = panel;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if (panel.getPaths() == null)
            return;

        if(panel.getPaths().isEmpty())
            return;

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            panel.getPathsManager().prevPath();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            panel.getPathsManager().nextPath();
        }

        panel.setFocusable(true);
        panel.requestFocusInWindow();

        panel.repaint();
    }

    public void keyReleased(KeyEvent e) {

    }

}
