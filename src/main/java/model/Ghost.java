package model;

import com.googlecode.lanterna.TextColor;

public class Ghost {
    private TextColor.ANSI ghostColor;
    private int rowPosition;
    private int colPosition;

    public Ghost(TextColor.ANSI ghostColor, int rowPosition, int colPosition) {
        this.ghostColor = ghostColor;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
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
}
