package domain.model;

public class ExpectedRtp {
    private double baseGame;
    private double bonusGame;
    private double total;

    public ExpectedRtp(double baseGame, double bonusGame, double total) {
        this.baseGame = baseGame;
        this.bonusGame = bonusGame;
        this.total = total;
    }
}
