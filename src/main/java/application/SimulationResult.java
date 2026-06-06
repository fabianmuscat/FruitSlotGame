package application;

public class SimulationResult {
    private final long rounds;
    private final long totalBet;
    private final long baseGameWin;
    private final long bonusGameWin;
    private final long bonusTriggers;
    private final long baseGameHitRounds;

    public long getRounds() {
        return rounds;
    }

    public long getTotalBet() {
        return totalBet;
    }

    public long getBaseGameWin() {
        return baseGameWin;
    }

    public long getBonusGameWin() {
        return bonusGameWin;
    }

    public long getBonusTriggers() {
        return bonusTriggers;
    }

    public long getBaseGameHitRounds() {
        return baseGameHitRounds;
    }

    public SimulationResult(long rounds, long totalBet, long baseGameWin, long bonusGameWin, long bonusTriggers, long baseGameHitRounds) {
        this.rounds = rounds;
        this.totalBet = totalBet;
        this.baseGameWin = baseGameWin;
        this.bonusGameWin = bonusGameWin;
        this.bonusTriggers = bonusTriggers;
        this.baseGameHitRounds = baseGameHitRounds;
    }
}
