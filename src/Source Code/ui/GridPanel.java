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
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = new Node(i, j);
            }
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;
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
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
                g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
