package domain.dto;

public class WeightedValueConfig {
    private int value;
    private int multiplier;
    private int weight;

    public int getValue() {
        return value;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getWeight() {
        return weight;
    }

    public WeightedValueConfig(int value, int multiplier, int weight) {
        this.value = value;
        this.multiplier = multiplier;
        this.weight = weight;
    }
}
