package domain.dto;

public class BonusSymbolConfig {
    private String symbol;
    private int baseGamePayout;
    private String effect;

    public String getSymbol() {
        return symbol;
    }

    public int getBaseGamePayout() {
        return baseGamePayout;
    }

    public String getEffect() {
        return effect;
    }

    public BonusSymbolConfig(String symbol, int baseGamePayout, String effect) {
        this.symbol = symbol;
        this.baseGamePayout = baseGamePayout;
        this.effect = effect;
    }
}
