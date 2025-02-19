import java.awt.*;
import javax.swing.*;

// Main class to initialize the Pathfinding Visualizer GUI
public class Main {

    // Entry point of the application
    public static void main(String[] args) {
        // Create a new JFrame with the title "Pathfinding Visualizer"
        JFrame frame = new JFrame("Pathfinding Visualizer");

        // Set default close operation to exit the application when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame to 800x600 pixels
        frame.setSize(800, 600);

        // Create an instance of GridPanel which represents the main visualization area
        GridPanel gridPanel = new GridPanel();

        // Create an instance of ControlsPanel to provide user interaction controls
        ControlsPanel controlsPanel = new ControlsPanel(gridPanel);

        // Set the layout of the frame to BorderLayout for component arrangement
        frame.setLayout(new BorderLayout());

        // Add the controls panel to the top of the frame
        frame.add(controlsPanel, BorderLayout.NORTH);

        // Add the grid panel to the center of the frame
        frame.add(gridPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}
