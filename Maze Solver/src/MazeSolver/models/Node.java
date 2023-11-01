package MazeSolver.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Node {

    private int key;
    private int x;
    private int y;
    private Color cellColor;

    public static final int cellSize = 50;
    public static final Color cellEmptyColor = new Color(142, 150, 116, 255);
    public static final Color cellStartColor = new Color(100, 245, 78);
    public static final Color cellInaccessibleColor = new Color(220, 14, 14);
    public static final Color cellBorderColor = new Color(0, 0, 0);
    public static final Color cellWallColor = new Color(0, 0, 0);

    public Node(int key, int x, int y) {
        this.key = key;
        this.x = x;
        this.y = y;
        cellColor = cellStartColor;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getKey() {
        return key;
    }

    public void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {

        g.setColor(cellColor);
        g.fillRect(x, y, cellSize, cellSize);
        g.setColor(cellBorderColor);
        g.drawRect(x, y, cellSize, cellSize);
    }

    public boolean containsPoint(Point point) {
        return x <= point.x && point.x < x + cellSize && y <= point.y && point.y < y + cellSize;
    }


}
