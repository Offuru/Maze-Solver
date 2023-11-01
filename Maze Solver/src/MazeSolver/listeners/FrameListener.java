package MazeSolver.listeners;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;

import MazeSolver.Panel;
import MazeSolver.models.Node;

public class FrameListener implements ComponentListener {

    private final JFrame frame;
    private final Panel panel;

    public FrameListener(JFrame frame, Panel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    @Override
    public void componentResized(ComponentEvent e) {

        int width = frame.getWidth();
        int height = frame.getHeight()-2*(frame.getInsets().top+frame.getInsets().bottom);

        if (panel.getMaze() == null)
            return;

        panel.getMaze().setPosition((width - panel.getMaze().getWidth() * Node.cellSize) / 2, (height - panel.getMaze().getHeight() * Node.cellSize) / 2);

        for (int i = 0; i < panel.getMaze().getHeight(); i++)
            for (int j = 0; j < panel.getMaze().getWidth(); j++)
                panel.getMaze().getNode(i, j).setPosition(panel.getMaze().getX() + i * Node.cellSize, panel.getMaze().getY() + j * Node.cellSize);
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }
}
