package model;

import com.googlecode.lanterna.TextColor;
import controller.utils.Path;

public class Ghost {
    private TextColor.ANSI ghostColor;
    private int rowPosition;
    private int colPosition;
    private Path latestPath;

    public Ghost(TextColor.ANSI ghostColor, int rowPosition, int colPosition) {
        this.ghostColor = ghostColor;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
        this.latestPath = Path.NOWHERE;
    }


    public TextColor.ANSI getGhostColor() {
        return ghostColor;
    }

    public void setGhostColor(TextColor.ANSI ghostColor) {
        this.ghostColor = ghostColor;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public Path getLatestPath() {
        return latestPath;
    }

    public void setLatestPath(Path latestPath) {
        this.latestPath = latestPath;
    }
}
