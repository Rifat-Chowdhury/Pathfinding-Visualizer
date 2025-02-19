# Pathfinding Visualizer

Pathfinding Visualizer is a Java-based pathfinding visualization tool that demonstrates multiple search algorithms on a grid-based environment. It includes implementations of various algorithms such as A\*, Dijkstra, BFS, and DFS, allowing users to visualize their performance in real-time.

## Features

- Supports multiple pathfinding algorithms:
  - **Breadth-First Search (BFS)**
  - **Depth-First Search (DFS)**
  - **A**\* Algorithm\*\*
  - **Dijkstra's Algorithm**
- Grid-based visualization with real-time updates.
- Interactive user controls to initiate pathfinding and reset the grid.

## Installation & Setup

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- An IDE such as IntelliJ IDEA, Eclipse, or NetBeans (optional but recommended).

### Steps to Run

1. Clone or download the repository.
2. Open the project in your preferred IDE.
3. Ensure that the `src` directory is set as the source folder.
4. Compile and run the `Main.java` file.

Alternatively, you can compile and run the project from the command line:

```sh
javac -d out src/*.java
java -cp out Main
```

## Project Structure

```
Pathfinding Visualizer/
│── src/
│   ├── AStar.java           # A* Algorithm implementation
│   ├── BFS.java             # Breadth-First Search algorithm
│   ├── DFS.java             # Depth-First Search algorithm
│   ├── Dijkstra.java        # Dijkstra's algorithm implementation
│   ├── Node.java            # Represents a node in the grid
│   ├── GridPanel.java       # Handles grid visualization
│   ├── ControlsPanel.java   # UI Controls for interaction
│   ├── Main.java            # Main entry point of the application
│── out/                     # Compiled class files
│── .idea/                   # IntelliJ IDEA settings
│── .gitignore               # Version control ignore list
```

## Usage

- The program presents a grid where the user can select a start and end node.
- Users can choose one of the available pathfinding algorithms.
- The selected algorithm will run, and the shortest path (if found) will be displayed.
- Users can reset the grid and test other algorithms.

## License

This project is open-source and available under the GPL-3.0 License.

