import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

// GridPanel class represents a panel for visualizing a grid-based system
public class GridPanel extends JPanel {
    private static final int ROWS = 20; // Number of rows in the grid
    private static final int COLS = 20; // Number of columns in the grid
    private Node[][] grid; // 2D array to store grid nodes
    private Node startNode, endNode; // Start and end nodes for pathfinding
    private int hoverRow = -1, hoverCol = -1; // Coordinates of the hovered cell
    private List<Node> finalPath = new ArrayList<>(); // List to store the final computed path
    private boolean isAnimating = false; // Flag to track animation state

    // Constructor initializes the grid and sets up mouse interaction
    public GridPanel() {
        grid = new Node[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                grid[row][col] = new Node(row, col);
            }
        }

        // Mouse listener to handle cell selection for start, end, and wall nodes
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (isAnimating) return; // Prevent interaction during animation
                int cellWidth = getWidth() / COLS;
                int cellHeight = getHeight() / ROWS;
                int col = e.getX() / cellWidth;
                int row = e.getY() / cellHeight;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (startNode == null) {
                        startNode = grid[row][col];
                        startNode.setStart(true);
                    } else if (endNode == null) {
                        endNode = grid[row][col];
                        endNode.setEnd(true);
                    } else {
                        grid[row][col].setWall(!grid[row][col].isWall());
                    }
                }
                repaint();
            }
        });
    }

    // Getters for grid, start node, and end node
    public Node[][] getGrid() { return grid; }
    public Node getStartNode() { return startNode; }
    public Node getEndNode() { return endNode; }

    // Resets only the path-related properties of nodes without affecting walls or start/end nodes
    public void resetPath() {
        for (Node[] row : grid) {
            for (Node node : row) {
                node.setVisited(false);
                node.setPath(false);
                node.setTraversal(false);
                node.setBacktrack(false);
            }
        }
        finalPath.clear();
        repaint();
    }

    // Resets the entire grid, clearing start, end, and walls
    public void resetGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                grid[row][col] = new Node(row, col);
            }
        }
        startNode = null;
        endNode = null;
        finalPath.clear();
        repaint();
    }

    // Generates a random grid with start, end, and random obstacles
    public void generateRandomGrid() {
        resetGrid(); // Reset grid before generating a new one
        Random rand = new Random();
        startNode = grid[rand.nextInt(ROWS)][rand.nextInt(COLS)];
        startNode.setStart(true);
        endNode = grid[rand.nextInt(ROWS)][rand.nextInt(COLS)];
        endNode.setEnd(true);

        // Populate grid with random obstacles
        for (int i = 0; i < ROWS * COLS / 4; i++) {
            int row = rand.nextInt(ROWS);
            int col = rand.nextInt(COLS);
            if (!grid[row][col].isStart() && !grid[row][col].isEnd()) {
                grid[row][col].setWall(true);
            }
        }
        repaint();
    }

    // Animates the path traversal by iterating over the given path list
    public void animatePath(List<Node> path) {
        new Thread(() -> {
            isAnimating = true;
            for (Node node : path) {
                node.setPath(true);
                repaint();
                try { Thread.sleep(100); } catch (InterruptedException ex) {}
            }
            isAnimating = false;
        }).start();
    }

    // Triggers a repaint of the grid
    public void repaintGrid() {
        repaint();
    }

    // Paints the grid and updates colors based on node states
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = getWidth() / COLS;
        int cellHeight = getHeight() / ROWS;

        for (Node[] row : grid) {
            for (Node node : row) {
                if (node.isStart()) g.setColor(Color.GREEN);
                else if (node.isEnd()) g.setColor(Color.RED);
                else if (node.isWall()) g.setColor(Color.BLACK);
                else if (node.isTraversal()) g.setColor(Color.MAGENTA);  // 🟣 Purple for traversal
                else if (node.isBacktrack()) g.setColor(Color.YELLOW);   // 🟨 Yellow for backtracking
                else if (node.isPath()) g.setColor(Color.BLUE);
                else g.setColor(Color.WHITE);

                g.fillRect(node.getCol() * cellWidth, node.getRow() * cellHeight, cellWidth, cellHeight);
                g.setColor(Color.GRAY);
                g.drawRect(node.getCol() * cellWidth, node.getRow() * cellHeight, cellWidth, cellHeight);
            }
        }

        // Draw hover coordinates if applicable
        if (hoverRow >= 0 && hoverCol >= 0) {
            g.setColor(Color.RED);
            g.drawString("(" + hoverRow + ", " + hoverCol + ")", hoverCol * cellWidth + 5, hoverRow * cellHeight + 15);
        }
    }
}
