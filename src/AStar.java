import java.util.*;

/**
 * Implements the A* search algorithm for pathfinding.
 * This class finds the shortest path between a start node and an end node.
 */
public class AStar {
    /**
     * Performs the A* search algorithm on a grid to find the shortest path.
     *
     * @param grid The 2D array representing the grid of nodes.
     * @param startNode The starting node for the search.
     * @param endNode The target node to reach.
     * @param panel The GridPanel used for visualization.
     */
    public void search(Node[][] grid, Node startNode, Node endNode, GridPanel panel) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.getF()));
        Map<Node, Node> cameFrom = new HashMap<>();
        startNode.setG(0);
        startNode.setH(heuristic(startNode, endNode));
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

                int tentativeG = current.getG() + 1;
                if (tentativeG < neighbor.getG()) {
                    cameFrom.put(neighbor, current);
                    neighbor.setG(tentativeG);
                    neighbor.setH(heuristic(neighbor, endNode));
                    neighbor.setF(neighbor.getG() + neighbor.getH());
                    openSet.add(neighbor);
                }
            }
        }
    }

    /**
     * Computes the heuristic cost estimate from node `a` to node `b`.
     * Uses Manhattan distance as the heuristic function.
     *
     * @param a The current node.
     * @param b The target node.
     * @return The estimated cost from node `a` to node `b`.
     */
    private int heuristic(Node a, Node b) {
        return Math.abs(a.getRow() - b.getRow()) + Math.abs(a.getCol() - b.getCol());
    }

    /**
     * Reconstructs the shortest path by backtracking from the end node.
     *
     * @param cameFrom A map tracking the previous node for each node in the path.
     * @param endNode The endpoint of the path.
     * @return A list of nodes representing the reconstructed path.
     */
    private List<Node> reconstructPath(Map<Node, Node> cameFrom, Node endNode) {
        List<Node> path = new ArrayList<>();
        for (Node at = endNode; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Retrieves the valid neighboring nodes of a given node in the grid.
     *
     * @param grid The 2D array representing the grid of nodes.
     * @param node The current node whose neighbors are to be found.
     * @return A list of neighboring nodes.
     */
    private List<Node> getNeighbors(Node[][] grid, Node node) {
        List<Node> neighbors = new ArrayList<>();
        int row = node.getRow();
        int col = node.getCol();

        if (row > 0) neighbors.add(grid[row - 1][col]);
        if (row < grid.length - 1) neighbors.add(grid[row + 1][col]);
        if (col > 0) neighbors.add(grid[row][col - 1]);
        if (col < grid[0].length - 1) neighbors.add(grid[row][col + 1]);

        return neighbors;
    }
}
