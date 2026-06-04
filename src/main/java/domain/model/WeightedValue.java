package domain.model;

public class WeightedValue {
    private int weight;
    private int value;

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public WeightedValue(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}