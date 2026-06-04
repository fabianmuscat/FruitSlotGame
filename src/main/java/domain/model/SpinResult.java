package domain.model;

public class SpinResult {
    private Symbol[][] grid;
    int[] reelStops;

    public Symbol[][] getGrid() {
        return grid;
    }

    public int[] getReelStops() {
        return reelStops;
    }

    public SpinResult(Symbol[][] grid, int[] reelStops) {
        this.grid = grid;
        this.reelStops = reelStops;
    }
}
