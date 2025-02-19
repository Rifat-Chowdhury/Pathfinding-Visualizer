package models;

public class Node {
    public int x, y;
    public boolean isWall, isStart, isEnd, isVisited;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.isWall = false;
        this.isStart = false;
        this.isEnd = false;
        this.isVisited = false;
    }
}
