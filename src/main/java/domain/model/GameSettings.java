package domain.model;

public class GameSettings {
    private int rows;
    private int reels;
    private int paylines;
    private int betAmount;
    private long simulationRounds;

    public GameSettings(int rows, int reels, int paylines, int betAmount, long simulationRounds) {
        this.rows = rows;
        this.reels = reels;
        this.paylines = paylines;
        this.betAmount = betAmount;
        this.simulationRounds = simulationRounds;
    }
}
