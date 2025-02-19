package algorithms;

import models.Node;
import java.util.List;

public class DFS {
    public static boolean search(Node[][] grid, Node start, Node end, List<Node> path) {
        if (start == null || start.isWall || start.isVisited) return false;
        start.isVisited = true;
        path.add(start);
        if (start == end) return true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : directions) {
            int newX = start.x + dir[0], newY = start.y + dir[1];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                if (search(grid, grid[newX][newY], end, path)) return true;
            }
        }
        path.remove(start); // Backtrack
        return false;
    }
}