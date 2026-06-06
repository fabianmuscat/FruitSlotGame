package domain.model;

public class BonusResult {
    private boolean triggered;
    private int winAmount;

    public boolean isTriggered() {
        return triggered;
    }

    public int getWinAmount() {
        return winAmount;
    }

    public BonusResult(boolean triggered, int winAmount) {
        this.triggered = triggered;
        this.winAmount = winAmount;
    }
}
