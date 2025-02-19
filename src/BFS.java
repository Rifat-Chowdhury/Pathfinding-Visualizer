import java.util.*;

public class BFS {
    // Performs Breadth-First Search (BFS) to find the shortest path from startNode to endNode.
    public void search(Node[][] grid, Node startNode, Node endNode, GridPanel panel) {
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> cameFrom = new HashMap<>();
        queue.add(startNode);
        cameFrom.put(startNode, null);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            current.setVisited(true);
            panel.repaintGrid();

            // If the end node is found, reconstruct and animate the path.
            if (current == endNode) {
                List<Node> finalPath = reconstructPath(cameFrom, endNode);
                panel.animatePath(finalPath);
                return;
            }

            // Add unvisited and non-wall neighbors to the queue.
            for (Node neighbor : getNeighbors(grid, current)) {
                if (!neighbor.isVisited() && !neighbor.isWall() && !cameFrom.containsKey(neighbor)) {
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }
    }

    // Reconstructs the shortest path by backtracking from the end node.
    private List<Node> reconstructPath(Map<Node, Node> cameFrom, Node endNode) {
        List<Node> path = new ArrayList<>();
        for (Node at = endNode; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // Retrieves the four adjacent neighbors (up, down, left, right) of a given node.
    private List<Node> getNeighbors(Node[][] grid, Node node) {
        List<Node> neighbors = new ArrayList<>();
        int row = node.getRow();
        int col = node.getCol();

        if (row > 0) neighbors.add(grid[row - 1][col]);   // Up
        if (row < grid.length - 1) neighbors.add(grid[row + 1][col]);   // Down
        if (col > 0) neighbors.add(grid[row][col - 1]);   // Left
        if (col < grid[0].length - 1) neighbors.add(grid[row][col + 1]);   // Right

        return neighbors;
    }
}
