package MazeSolver.listeners;

import java.awt.event.MouseEvent;

import MazeSolver.Panel;

public class MouseListener implements javax.swing.event.MouseInputListener {

    Panel panel;

    public MouseListener(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        for (int i = 0; i < panel.getMaze().getHeight(); i++)
            for (int j = 0; j < panel.getMaze().getWidth(); j++)
                if (panel.getMaze().getNode(i, j).containsPoint(e.getPoint()) && panel.getMaze().getNode(i, j).getKey() != -1) {
                    panel.setStartNode(panel.getMaze().getNode(i, j));
                }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
