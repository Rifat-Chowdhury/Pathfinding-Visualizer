import java.util.*;

public class Dijkstra {
    // Implements Dijkstra's algorithm for pathfinding in a grid-based system.
    public void search(Node[][] grid, Node startNode, Node endNode, GridPanel panel) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getG));
        Map<Node, Node> cameFrom = new HashMap<>();

        openSet.clear();
        cameFrom.clear();

        startNode.setG(0);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            current.setVisited(true);
            panel.repaintGrid();

            if (current == endNode) {
                List<Node> finalPath = reconstructPath(cameFrom, endNode);
                panel.animatePath(finalPath);
                return;
            }

            for (Node neighbor : getNeighbors(grid, current)) {
                if (neighbor.isWall() || neighbor.isVisited()) continue;

                int newCost = current.getG() + 1;
                if (newCost < neighbor.getG()) {
                    cameFrom.put(neighbor, current);
                    neighbor.setG(newCost);
                    openSet.add(neighbor);
                }
            }
        }
    }

    // Reconstructs the shortest path from the end node to the start node using the cameFrom map.
    private List<Node> reconstructPath(Map<Node, Node> cameFrom, Node endNode) {
        List<Node> path = new ArrayList<>();
        for (Node at = endNode; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // Retrieves the valid neighboring nodes of the given node in the grid.
    private List<Node> getNeighbors(Node[][] grid, Node node) {
        List<Node> neighbors = new ArrayList<>();
        int row = node.getRow();
        int col = node.getCol();

        if (row > 0) neighbors.add(grid[row - 1][col]); // Up
        if (row < grid.length - 1) neighbors.add(grid[row + 1][col]); // Down
        if (col > 0) neighbors.add(grid[row][col - 1]); // Left
        if (col < grid[0].length - 1) neighbors.add(grid[row][col + 1]); // Right

        return neighbors;
    }
}
