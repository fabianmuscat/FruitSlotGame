package domain.dto;

import java.util.List;

public class BonusGameConfig {
    private BonusTriggerConfig trigger;
    private List<WeightedValueConfig> coinTable;
    private List<WeightedValueConfig> diceTable;

    public BonusTriggerConfig getTrigger() {
        return trigger;
    }

    public List<WeightedValueConfig> getCoinTable() {
        return coinTable;
    }

    public List<WeightedValueConfig> getDiceTable() {
        return diceTable;
    }

    public BonusGameConfig(BonusTriggerConfig trigger, List<WeightedValueConfig> coinTable, List<WeightedValueConfig> diceTable) {
        this.trigger = trigger;
        this.coinTable = coinTable;
        this.diceTable = diceTable;
    }
}
