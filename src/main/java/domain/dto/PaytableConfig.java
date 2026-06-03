package domain.dto;

import java.util.Map;

public class PaytableConfig {
    private Map<String, Integer> baseGamePayouts;
    private BonusSymbolConfig bonusSymbol;

    public Map<String, Integer> getBaseGamePayouts() {
        return baseGamePayouts;
    }

    public BonusSymbolConfig getBonusSymbol() {
        return bonusSymbol;
    }

    public PaytableConfig(Map<String, Integer> baseGamePayouts, BonusSymbolConfig bonusSymbol) {
        this.baseGamePayouts = baseGamePayouts;
        this.bonusSymbol = bonusSymbol;
    }
}
