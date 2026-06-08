package domain.dto;

public class BonusTriggerConfig {
    private String symbol;
    private int minimumVisibleCount;

    public String getSymbol() {
        return symbol;
    }

    public int getMinimumVisibleCount() {
        return minimumVisibleCount;
    }

    public BonusTriggerConfig(String symbol, int minimumVisibleCount) {
        this.symbol = symbol;
        this.minimumVisibleCount = minimumVisibleCount;
    }
}
