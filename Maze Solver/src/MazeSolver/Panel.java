package MazeSolver;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

import MazeSolver.algorithms.BFS;
import MazeSolver.listeners.KeyboardListener;
import MazeSolver.listeners.MouseListener;
import MazeSolver.managers.PathsManager;
import MazeSolver.models.Graph;
import MazeSolver.models.Maze;
import MazeSolver.models.Node;

public class Panel extends JPanel {

    private Maze maze;
    private Node startNode;
    private List<List<Node>> paths;
    private final PathsManager pathsManager;
    private final KeyboardListener keyboardListener;
    private final MouseListener mouseListener;
    private final Graph graph;
    private static final Color backgroundColor = new Color(86, 151, 206);

    private BFS bfs;

    public Panel(String filePath) {

        setFocusable(true);
        requestFocusInWindow();

        setBackground(backgroundColor);
        buildMaze(filePath);
        graph = new Graph(maze);

        if (startNode != null) {
            bfs = new BFS(graph, startNode, maze);
            paths = bfs.getPaths();
        }

        pathsManager = new PathsManager(this);

        addKeyListener(keyboardListener = new KeyboardListener(this));
        addMouseListener(mouseListener = new MouseListener(this));

        repaint();
    }

    private void buildMaze(String filePath) {

        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            maze = new Maze(sc.nextInt(), sc.nextInt());
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
        if (paths != null)
            pathsManager.drawPath(g);

        setFocusable(true);
        requestFocusInWindow();
    }

    public List<List<Node>> getPaths() {
        return paths;
    }

    public PathsManager getPathsManager() {
        return pathsManager;
    }

    public Maze getMaze() {
        return maze;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {

        if (this.startNode != null) {
            for (List<Node> row : maze.getMatrix())
                for (Node node : row)
                    if (node.getKey() != -1)
                        node.setCellColor(Node.cellEmptyColor);
        }

        if (this.startNode != null)
            this.startNode.setCellColor(Node.cellEmptyColor);
        this.startNode = startNode;
        this.startNode.setCellColor(Node.cellStartColor);
        bfs = new BFS(graph, this.startNode, maze);
        paths = bfs.getPaths();
        pathsManager.setPaths(paths);
        pathsManager.setCurrentPath(0);

        repaint();
    }
}
