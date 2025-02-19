import java.awt.*;
import javax.swing.*;
/**
 * ControlsPanel is a GUI component that contains buttons for different pathfinding algorithms
 * and grid actions. It allows the user to select an algorithm and execute it on the grid.
 */
public class ControlsPanel extends JPanel {
    /**
     * Initializes the control panel with buttons for various pathfinding algorithms and grid modifications.
     * @param gridPanel The grid panel where pathfinding will be visualized.
     */
    public ControlsPanel(GridPanel gridPanel) {
        // Creates buttons for pathfinding algorithms
        JButton bfsButton = new JButton("Run BFS");
        JButton dfsButton = new JButton("Run DFS");
        JButton aStarButton = new JButton("Run A*");
        JButton dijkstraButton = new JButton("Run Dijkstra");
        JButton undoPathButton = new JButton("Undo Path");
        JButton resetButton = new JButton("Reset Grid");
        JButton randomButton = new JButton("Random Grid");

        // Adds an event listener to execute BFS when clicked
        bfsButton.addActionListener(e -> {
            gridPanel.resetPath();
            BFS bfs = new BFS();
            bfs.search(gridPanel.getGrid(), gridPanel.getStartNode(), gridPanel.getEndNode(), gridPanel);
        });

        // Adds an event listener to execute DFS when clicked
        dfsButton.addActionListener(e -> {
            gridPanel.resetPath();
            DFS dfs = new DFS();
            dfs.search(gridPanel.getGrid(), gridPanel.getStartNode(), gridPanel.getEndNode(), gridPanel);
        });

        // Adds an event listener to execute A* when clicked
        aStarButton.addActionListener(e -> {
            gridPanel.resetPath();

            if (gridPanel.getStartNode() == null || gridPanel.getEndNode() == null) {
                JOptionPane.showMessageDialog(null, "Please select a start and end node before running the algorithm.");
                return;
            }

            AStar aStar = new AStar();
            aStar.search(gridPanel.getGrid(), gridPanel.getStartNode(), gridPanel.getEndNode(), gridPanel);
        });

        // Adds an event listener to execute Dijkstra's algorithm when clicked
        dijkstraButton.addActionListener(e -> {
            gridPanel.resetPath();

            if (gridPanel.getStartNode() == null || gridPanel.getEndNode() == null) {
                JOptionPane.showMessageDialog(null, "Please select a start and end node before running the algorithm.");
                return;
            }
            Dijkstra dijkstra = new Dijkstra();
            dijkstra.search(gridPanel.getGrid(), gridPanel.getStartNode(), gridPanel.getEndNode(), gridPanel);
        });
        // Button to undo the last pathfinding operation
        undoPathButton.addActionListener(e -> {
            gridPanel.resetPath();
        });
        // Button to reset the entire grid
        resetButton.addActionListener(e -> {
            gridPanel.resetGrid();
        });
        // Button to generate a random grid layout
        randomButton.addActionListener(e -> {
            gridPanel.generateRandomGrid();
        });
        // Adds buttons to the control panel
        add(bfsButton);
        add(dfsButton);
        add(aStarButton);
        add(dijkstraButton);
        add(undoPathButton);
        add(resetButton);
        add(randomButton);
    }
}
