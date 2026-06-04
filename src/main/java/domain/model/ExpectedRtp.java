package domain.model;

public class ExpectedRtp {
    private double baseGame;
    private double bonusGame;
    private double total;

    public double getBaseGame() {
        return baseGame;
    }

    public double getBonusGame() {
        return bonusGame;
    }

    public double getTotal() {
        return total;
    }

    public ExpectedRtp(double baseGame, double bonusGame, double total) {
        this.baseGame = baseGame;
        this.bonusGame = bonusGame;
        this.total = total;
    }
}
