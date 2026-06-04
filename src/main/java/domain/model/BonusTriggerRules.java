package domain.model;

public class BonusTriggerRules {
    private Symbol triggerSymbol;
    private int minimumVisibleCount;

    public Symbol getTriggerSymbol() {
        return triggerSymbol;
    }

    public int getMinimumVisibleCount() {
        return minimumVisibleCount;
    }

    public BonusTriggerRules(Symbol triggerSymbol, int minimumVisibleCount) {
        this.triggerSymbol = triggerSymbol;
        this.minimumVisibleCount = minimumVisibleCount;
    }
}
