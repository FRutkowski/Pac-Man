package model;

public class PacManCurrentPosition {
    private int y = 17;
    private int x = 25;


    public PacManCurrentPosition(int row, int col) {
        this.y = row;
        this.x = col;
    }

    public int getRow() {
        return y;
    }

    public int getColumn() {
        return x;
    }

    public void setRow(int newRow) {
        y = newRow;
    }

    public void setColumn(int newColumn) {
        x = newColumn;
    }
}
