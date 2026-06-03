package domain.dto;

public class BonusTriggerConfig {
    private String symbol;
    private int minimumVisibleCount;
    private String scope;

    public String getSymbol() {
        return symbol;
    }

    public int getMinimumVisibleCount() {
        return minimumVisibleCount;
    }

    public String getScope() {
        return scope;
    }

    public BonusTriggerConfig(String symbol, int minimumVisibleCount, String scope) {
        this.symbol = symbol;
        this.minimumVisibleCount = minimumVisibleCount;
        this.scope = scope;
    }
}
