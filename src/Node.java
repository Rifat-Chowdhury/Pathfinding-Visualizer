public class Node {
    // Row and column indices of the node in the grid
    private int row, col;

    // Flags for different states of the node
    private boolean visited, isWall, isStart, isEnd, isPath, isTraversal, isBacktrack;

    // Variables used for A* and Dijkstra's algorithm
    private int g, h, f; // g = cost from start, h = heuristic cost, f = total cost

    // Constructor to initialize the node with default values
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.visited = false;
        this.isWall = false;
        this.isStart = false;
        this.isEnd = false;
        this.isPath = false;
        this.isTraversal = false;
        this.isBacktrack = false;
        this.g = Integer.MAX_VALUE; // Default to a high value, representing unvisited nodes
        this.h = 0;
        this.f = Integer.MAX_VALUE;
    }

    // Getter methods to access private fields
    public int getRow() { return row; } // Get row index
    public int getCol() { return col; } // Get column index
    public boolean isVisited() { return visited; } // Check if the node has been visited
    public boolean isWall() { return isWall; } // Check if the node is a wall
    public boolean isStart() { return isStart; } // Check if the node is the start node
    public boolean isEnd() { return isEnd; } // Check if the node is the end node
    public boolean isPath() { return isPath; } // Check if the node is part of the final path
    public boolean isTraversal() { return isTraversal; } // Check if the node is in the traversal path
    public boolean isBacktrack() { return isBacktrack; } // Check if the node is part of the backtracking process

    // Setter methods to modify private fields
    public void setVisited(boolean visited) { this.visited = visited; } // Set visited status
    public void setWall(boolean isWall) { this.isWall = isWall; } // Mark node as a wall
    public void setStart(boolean isStart) { this.isStart = isStart; } // Mark node as start
    public void setEnd(boolean isEnd) { this.isEnd = isEnd; } // Mark node as end
    public void setPath(boolean isPath) { this.isPath = isPath; } // Mark node as part of the final path
    public void setTraversal(boolean isTraversal) { this.isTraversal = isTraversal; } // Mark node as part of traversal
    public void setBacktrack(boolean isBacktrack) { this.isBacktrack = isBacktrack; } // Mark node as part of backtracking

    // Getter methods for A* and Dijkstra values
    public int getG() { return g; } // Get g cost (cost from start node)
    public int getH() { return h; } // Get h cost (heuristic cost to end node)
    public int getF() { return f; } // Get f cost (total cost: g + h)

    // Setter methods for A* and Dijkstra values
    public void setG(int g) { this.g = g; } // Set g cost
    public void setH(int h) { this.h = h; } // Set h cost
    public void setF(int f) { this.f = f; } // Set f cost
}
