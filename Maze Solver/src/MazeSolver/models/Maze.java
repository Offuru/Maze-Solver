package MazeSolver.models;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

public class Maze {

    private final List<List<Node>> matrix;
    private int x;
    private int y;
    private final int width;
    private final int height;
    private int nodeCount;

    public Maze(int height, int width) {

        this.height = height;
        this.width = width;
        this.x = (800 - width * Node.cellSize) / 2;
        this.y = (800 - height * Node.cellSize) / 2;
        nodeCount = 0;

        matrix = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            List<Node> temp = new ArrayList<>();

            for (int j = 0; j < width; j++)
                temp.add(new Node(-1, x + j * Node.cellSize, y + i * Node.cellSize));
            matrix.add(temp);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Node getNode(int i, int j) {
        return matrix.get(i).get(j);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<List<Node>> getMatrix() {
        return matrix;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {

        for (List<Node> row : matrix)
            for (Node node : row)
                node.draw(g);
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    public boolean isInside(int i, int j) {
        return (0 <= i && i < height && 0 <= j && j < width);
    }
}
