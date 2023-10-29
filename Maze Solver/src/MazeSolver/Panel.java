package MazeSolver;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import MazeSolver.algorithms.BFS;
import MazeSolver.models.Graph;
import MazeSolver.models.Maze;
import MazeSolver.models.Node;

public class Panel extends JPanel {

    private Maze maze;
    Node startNode;
    private static final Color backgroundColor = new Color(86, 151, 206);

    public Panel(String filePath) {
        setBackground(backgroundColor);
        buildMaze(filePath);
        Graph graph = new Graph(maze);
        BFS bfs = new BFS(graph, startNode);

        repaint();
    }

    private final void buildMaze(String filePath) {

        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            maze = new Maze(this, sc.nextInt(), sc.nextInt());
            int n = 0;

            for (int i = 0; i < maze.getHeight(); i++)
                for (int j = 0; j < maze.getWidth(); j++) {

                    int value = sc.nextInt();

                    switch (value) {
                        case 0:
                            maze.getNode(i, j).setCellColor(Node.cellWallColor);
                            break;
                        case 1:
                            maze.getNode(i, j).setCellColor(Node.cellEmptyColor);
                            maze.getNode(i, j).setKey(n++);
                            break;
                        case 2:
                            maze.getNode(i, j).setCellColor(Node.cellStartColor);
                            maze.getNode(i, j).setKey(n++);
                            startNode = maze.getNode(i, j);
                            break;
                    }
                }
            maze.setNodeCount(n);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        maze.draw(g);

        setFocusable(true);
        requestFocusInWindow();
    }
}
