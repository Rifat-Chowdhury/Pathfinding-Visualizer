package ui;

import models.Node;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridPanel extends JPanel {
    private static final int GRID_SIZE = 20;
    private static final int CELL_SIZE = 30;
    private Node[][] grid;
    private Node startNode, endNode;

    public GridPanel() {
        this.grid = new Node[GRID_SIZE][GRID_SIZE];

        // Initialize grid with Node objects
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = new Node(i, j);
            }
        }

        System.out.println("GridPanel created!"); // Debugging to confirm initialization

        // Set panel size to match grid
        setPreferredSize(new Dimension(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE));

        // Mouse interaction to toggle walls and set start/end nodes
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;

                // Ensure index is within bounds
                if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        grid[x][y].isWall = !grid[x][y].isWall; // Toggle wall
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        if (startNode == null) {
                            startNode = grid[x][y];
                            startNode.isStart = true;
                        } else if (endNode == null) {
                            endNode = grid[x][y];
                            endNode.isEnd = true;
                        }
                    }
                    repaint(); // Refresh UI after change
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw each cell with correct color
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j].isWall) {
                    g.setColor(Color.BLACK);
                } else if (grid[i][j].isStart) {
                    g.setColor(Color.GREEN);
                } else if (grid[i][j].isEnd) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.WHITE);
                }

                // Swap i and j in fillRect and drawRect to correctly render the grid
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
