package algorithms;

import models.Node;
import java.util.*;

public class BFS {
    public static List<Node> search(Node[][] grid, Node start, Node end) {
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> parent = new HashMap<>();
        queue.add(start);
        start.isVisited = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == end) {
                return reconstructPath(parent, end);
            }
            for (Node neighbor : getNeighbors(grid, current)) {
                if (!neighbor.isVisited && !neighbor.isWall) {
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                    parent.put(neighbor, current);
                }
            }
        }
        return Collections.emptyList(); // No path found
    }

    private static List<Node> reconstructPath(Map<Node, Node> parent, Node end) {
        List<Node> path = new ArrayList<>();
        while (end != null) {
            path.add(end);
            end = parent.get(end);
        }
        Collections.reverse(path);
        return path;
    }
}
