package domain.model;

import java.util.ArrayList;
import java.util.List;

public class BonusCoinTable {
    private List<WeightedValue> coinValues;
    private List<WeightedValue> diceValues;

    public List<WeightedValue> getCoinValues() {
        return coinValues;
    }

    public List<WeightedValue> getDiceValues() {
        return diceValues;
    }

    public void setCoinValues(List<WeightedValue> coinValues) {
        this.coinValues = coinValues;
    }

    public void setDiceValues(List<WeightedValue> diceValues) {
        this.diceValues = diceValues;
    }

    public BonusCoinTable() {
    }

    public void addCoinValue(WeightedValue coinValue) {
        if (coinValues == null) {
            coinValues = new ArrayList<>();
        }

        this.coinValues.add(coinValue);
    }

    public void addDiceValue(WeightedValue diceValue) {
        if (diceValues == null) {
            diceValues = new ArrayList<>();
        }

        this.diceValues.add(diceValue);
    }
}
