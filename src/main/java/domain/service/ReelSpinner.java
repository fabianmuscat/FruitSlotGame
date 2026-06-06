package domain.service;

import domain.model.Reel;
import domain.model.SpinResult;
import domain.model.Symbol;

import java.util.List;
import java.util.Random;

public class ReelSpinner {
    private List<Reel> reels;
    private int visibleRows;
    private Random random;

    public List<Reel> getReels() {
        return reels;
    }

    public void setReels(List<Reel> reels) {
        this.reels = reels;
    }

    public int getVisibleRows() {
        return visibleRows;
    }

    public void setVisibleRows(int visibleRows) {
        this.visibleRows = visibleRows;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public ReelSpinner(List<Reel> reels, int visibleRows) {
        this.reels = reels;
        this.visibleRows = visibleRows;
        this.random = new Random();
    }

    public SpinResult spin() {
        Symbol[][] grid = new Symbol[visibleRows][reels.size()];
        int[] reelStops = new int[reels.size()];

        // Each reel receives an independent stop, then visible rows are read downward with reel wrapping.
        for (Reel reel : reels) {
            int column = reel.getId();
            int stop = random.nextInt(reel.getReelLength());
            reelStops[column] = stop;

            for (int row = 0; row < visibleRows; row++) {
                int symbolIndex = stop + row;
                Symbol symbol = reel.getSymbolAt(symbolIndex);
                grid[row][column] = symbol;
            }
        }

        return new SpinResult(grid, reelStops);
    }
}
