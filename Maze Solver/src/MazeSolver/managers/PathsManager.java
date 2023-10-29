package MazeSolver.managers;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.ArrayList;

import MazeSolver.models.Graph;
import MazeSolver.models.Maze;
import MazeSolver.models.Node;
import MazeSolver.Panel;


public class PathsManager {

    private List<List<Node>> paths;
    private final Panel panel;
    private int currentPath;
    private static final Color arrowColor = new Color(206, 68, 68);

    public PathsManager(Panel panel) {
        this.panel = panel;
        paths = panel.getPaths();
        currentPath = 1;

        for(int i=0;i<paths.size();i++){
            for(Node node:paths.get(i)){
                System.out.print(node.getKey()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void drawPath(Graphics g) {

        panel.setFocusable(true);
        panel.requestFocusInWindow();

        for (int i = 0; i < paths.get(currentPath).size() - 1; i++) {
            Node nodeA = paths.get(currentPath).get(i);
            Node nodeB = paths.get(currentPath).get(i + 1);

            Graphics2D g2d = (Graphics2D) g;
            AffineTransform initAT = g2d.getTransform();

            double theta = Math.atan2(nodeB.getY() - nodeA.getY(), nodeB.getX() - nodeA.getX());

            AffineTransform affineTransform = AffineTransform.getTranslateInstance(nodeB.getX() + (double) Node.cellSize / 2, nodeB.getY() + (double) Node.cellSize / 2);
            affineTransform.concatenate(AffineTransform.getRotateInstance(theta - Math.PI));
            g2d.setTransform(affineTransform);

            g2d.setColor(arrowColor);
            g2d.fillRect(0, 0, Math.max(Math.abs(nodeB.getY() - nodeA.getY()), Math.abs(nodeB.getX() - nodeA.getX())) - 10, 5);
            g2d.fillPolygon(new int[]{Node.cellSize / 2 + 10, Node.cellSize / 2 + 20, Node.cellSize / 2 + 10}, new int[]{-8, 2, 12}, 3);

            g2d.setTransform(initAT);
        }

        panel.repaint();
    }

    public void setCurrentPath(int currentPath) {
        this.currentPath = currentPath;
    }

    public void setPaths(List<List<Node>> paths) {
        this.paths = paths;
    }

    public void nextPath(Graphics g) {
        currentPath = (currentPath + 1) % paths.size();
    }

    public void prevPath(Graphics g) {
        currentPath--;
        if (currentPath < 0)
            currentPath = paths.size() - 1;
    }
}
