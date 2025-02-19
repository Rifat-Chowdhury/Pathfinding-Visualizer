package ui;

import algorithms.BFS;
import algorithms.DFS;
import models.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pathfinding Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 800);
            frame.setLayout(new BorderLayout());

            // Create Grid Panel
            GridPanel gridPanel = new GridPanel();
            frame.add(gridPanel, BorderLayout.CENTER);

            // Create Control Panel
            JPanel controlsPanel = new JPanel();
            JButton dfsButton = new JButton("Run DFS");
            JButton bfsButton = new JButton("Run BFS");
            JButton resetButton = new JButton("Reset");

            // Add Button Actions
            dfsButton.addActionListener(e -> {
                if (gridPanel.getStartNode() == null || gridPanel.getEndNode() == null) {
                    JOptionPane.showMessageDialog(frame, "Select a Start and End point!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                gridPanel.resetVisited();
                List<Node> path = new java.util.ArrayList<>();
                boolean found = DFS.search(gridPanel.getGrid(), gridPanel.getStartNode(), gridPanel.getEndNode(), path);
                if (found) gridPanel.showPath(path);
                else JOptionPane.showMessageDialog(frame, "No Path Found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            });

            bfsButton.addActionListener(e -> {
                if (gridPanel.getStartNode() == null || gridPanel.getEndNode() == null) {
                    JOptionPane.showMessageDialog(frame, "Select a Start and End point!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                gridPanel.resetVisited();
                List<Node> path = BFS.search(gridPanel.getGrid(), gridPanel.getStartNode(), gridPanel.getEndNode());
                if (!path.isEmpty()) gridPanel.showPath(path);
                else JOptionPane.showMessageDialog(frame, "No Path Found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            });

            resetButton.addActionListener(e -> gridPanel.resetGrid());

            // Add Buttons to Control Panel
            controlsPanel.add(dfsButton);
            controlsPanel.add(bfsButton);
            controlsPanel.add(resetButton);
            frame.add(controlsPanel, BorderLayout.SOUTH);

            // Finalize and Display Window
            frame.setVisible(true);
        });
    }
}
