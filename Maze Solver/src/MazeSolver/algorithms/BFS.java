package MazeSolver.algorithms;

import MazeSolver.models.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class BFS {

    private final Graph graph;
    private final List<Node> parents;
    private final List<Integer> distance;
    private final Node start;

    private final Maze maze;

    public BFS(Graph graph, Node start, Maze maze) {
        this.graph = graph;
        parents = new ArrayList<>();
        distance = new ArrayList<>();
        this.maze = maze;

        for (int i = 0; i < graph.getNodes().size(); i++) {
            parents.add(null);
            distance.add(Integer.MAX_VALUE);
        }

        this.start = start;

        bfs();
    }

    private void bfs() {

        Queue<Node> frontier = new LinkedList<>();
        frontier.add(start);
        distance.set(start.getKey(), 0);

        while (!frontier.isEmpty()) {

            Node node = frontier.poll();

            for (Node neighbour : graph.getAdjacencyList().get(node.getKey()))
                if (distance.get(neighbour.getKey()) == Integer.MAX_VALUE) {
                    distance.set(neighbour.getKey(), distance.get(node.getKey()) + 1);
                    parents.set(neighbour.getKey(), node);
                    frontier.add(neighbour);
                }
        }
    }

    public List<List<Node>> getPaths() {

        List<List<Node>> paths = new ArrayList<>();

        List<Node> exits = new ArrayList<>();

        for (int i = 0; i < maze.getHeight(); i++) {
            if (maze.getNode(i, 0).getKey() != -1)
                exits.add(maze.getNode(i, 0));
            if (maze.getNode(i, maze.getWidth() - 1).getKey() != -1)
                exits.add(maze.getNode(i, maze.getWidth() - 1));
        }

        for (int j = 1; j < maze.getWidth() - 1; j++) {
            if (maze.getNode(0, j).getKey() != -1)
                exits.add(maze.getNode(0, j));
            if (maze.getNode(maze.getHeight() - 1, j).getKey() != -1)
                exits.add(maze.getNode(maze.getHeight() - 1, j));
        }

        for (Node node : exits)
            if (distance.get(node.getKey()) != Integer.MAX_VALUE) {
                paths.add(new ArrayList<>());

                while (parents.get(node.getKey()) != null) {
                    paths.get(paths.size() - 1).add(node);
                    node = parents.get(node.getKey());
                }
                paths.get(paths.size() - 1).add(start);
            }

        for (Node exit : exits) {
            boolean found = false;
            for (List<Node> path : paths)
                if (path.get(0) == exit) {
                    found = true;
                    break;
                }

            if (!found)
                exit.setCellColor(Node.cellInaccessibleColor);
        }

        return paths;
    }


}
