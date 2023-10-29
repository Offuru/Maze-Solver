package MazeSolver.algorithms;

import MazeSolver.models.Node;
import MazeSolver.models.Graph;

import javax.swing.text.Style;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class BFS {

    private final Graph graph;
    private final List<Node> parents;
    private final List<Integer> distance;
    private final Node start;

    public BFS(Graph graph, Node start) {
        this.graph = graph;
        parents = new ArrayList<>();
        distance = new ArrayList<>();

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

        for (Node node : parents) {
            if (node == null)
                System.out.print("-1 ");
            else
                System.out.print(node.getKey() + " ");
        }

        System.out.println();

        for (Integer dist : distance)
            System.out.print((dist == Integer.MAX_VALUE ? "Inf" : dist) + " ");
    }


}
