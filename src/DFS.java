import java.util.*;

/**
 * Implements Depth-First Search (DFS) for pathfinding in a grid.
 */
public class DFS {

    /**
     * Performs a depth-first search on a given grid to find a path from startNode to endNode.
     *
     * @param grid The 2D array representing the grid.
     * @param startNode The starting node of the search.
     * @param endNode The target node to reach.
     * @param panel The grid panel used for visualization.
     */
    public void search(Node[][] grid, Node startNode, Node endNode, GridPanel panel) {
        Stack<Node> stack = new Stack<>(); // Stack to manage DFS traversal
        Map<Node, Node> cameFrom = new HashMap<>(); // Map to track the path
        stack.push(startNode);
        cameFrom.put(startNode, null);

        while (!stack.isEmpty()) {
            Node current = stack.pop(); // Retrieve the last added node
            current.setVisited(true); // Mark node as visited
            panel.repaintGrid(); // Update the grid visualization

            // If we reached the end node, reconstruct the path
            if (current == endNode) {
                List<Node> finalPath = reconstructPath(cameFrom, endNode);
                panel.animatePath(finalPath);
                return;
            }

            // Explore the neighbors of the current node
            for (Node neighbor : getNeighbors(grid, current)) {
                if (!neighbor.isVisited() && !neighbor.isWall() && !cameFrom.containsKey(neighbor)) {
                    stack.push(neighbor);
                    cameFrom.put(neighbor, current); // Track where we came from
                }
            }
        }
    }

    /**
     * Reconstructs the path from the end node to the start node.
     *
     * @param cameFrom A map tracking each node's predecessor.
     * @param endNode The target node to reconstruct the path from.
     * @return A list of nodes representing the path from start to end.
     */
    private List<Node> reconstructPath(Map<Node, Node> cameFrom, Node endNode) {
        List<Node> path = new ArrayList<>();
        for (Node at = endNode; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); // Reverse the path to get it from start to end
        return path;
    }

    /**
     * Retrieves the neighboring nodes of a given node in the grid.
     *
     * @param grid The 2D array representing the grid.
     * @param node The node whose neighbors are to be found.
     * @return A list of neighboring nodes.
     */
    private List<Node> getNeighbors(Node[][] grid, Node node) {
        List<Node> neighbors = new ArrayList<>();
        int row = node.getRow();
        int col = node.getCol();

        // Check and add valid neighboring nodes
        if (row > 0) neighbors.add(grid[row - 1][col]);   // Up
        if (row < grid.length - 1) neighbors.add(grid[row + 1][col]);   // Down
        if (col > 0) neighbors.add(grid[row][col - 1]);   // Left
        if (col < grid[0].length - 1) neighbors.add(grid[row][col + 1]);   // Right

        return neighbors;
    }
}
