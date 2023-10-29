package MazeSolver.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import MazeSolver.Panel;

public class KeyboardListener implements java.awt.event.KeyListener {

    Panel panel;

    public KeyboardListener(Panel panel){
        this.panel = panel;
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            panel.getPathsManager().prevPath(panel.getGraphics());
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            panel.getPathsManager().nextPath(panel.getGraphics());
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            System.out.println("Da");

        panel.setFocusable(true);
        panel.requestFocusInWindow();

        panel.repaint();
    }

    public void keyReleased(KeyEvent e){

    }

}
