package MazeSolver.models;

import MazeSolver.Panel;

import java.util.List;
import java.util.ArrayList;

public class Graph {

    private final List<List<Node>> adjacencyList;
    private final List<Node> nodes;

    public Graph(Maze maze) {
        adjacencyList = new ArrayList<>();
        nodes = new ArrayList<>();

        for (int i = 0; i < maze.getNodeCount(); i++) {
            adjacencyList.add(new ArrayList<>());
        }

        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++)
                if (maze.getNode(i, j).getKey() != -1) {

                    nodes.add(maze.getNode(i, j));
                    for (int k = 0; k < 4; k++) {
                        int newI = i + di[k], newJ = j + dj[k];
                        if (maze.isInside(newI, newJ) && maze.getNode(newI, newJ).getKey() != -1)
                            adjacencyList.get(maze.getNode(i, j).getKey()).add(maze.getNode(newI, newJ));
                    }
                }
        }

        for(int i=0;i<adjacencyList.size();i++){
            System.out.print(i+": ");
            for(Node node: adjacencyList.get(i)){
                System.out.print(node.getKey() + " ");
            }
            System.out.println();
        }

    }

}
