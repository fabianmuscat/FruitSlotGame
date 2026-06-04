package domain.model;

public class Position {
    private int row;
    private int reel;

    public int getRow() {
        return row;
    }

    public int getReel() {
        return reel;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setReel(int reel) {
        this.reel = reel;
    }

    public Position(int row, int reel) {
        this.row = row;
        this.reel = reel;
    }

    public Position() {
    }
}
