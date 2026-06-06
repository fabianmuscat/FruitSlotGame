package domain.model;

import java.util.HashMap;
import java.util.Map;

public class Paytable {
    private Map<Symbol, Integer> baseGamePayouts;

    public Map<Symbol, Integer> getBaseGamePayouts() {
        return baseGamePayouts;
    }

    public int getPayoutFor(Symbol symbol) {
        return baseGamePayouts.get(symbol);
    }

    public Paytable() {
    }

    public void addBaseGamePayout(Symbol symbol, int amount) {
        if (baseGamePayouts == null) {
            baseGamePayouts = new HashMap<>();
        }

        if (baseGamePayouts.containsKey(symbol)) {
            throw new IllegalStateException("Base game payouts already contains symbol " + symbol);
        }

        baseGamePayouts.put(symbol, amount);
    }
}
