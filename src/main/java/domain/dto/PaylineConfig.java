package domain.dto;

import java.util.List;

public class PaylineConfig {
    private int id;
    private String name;
    private List<List<Integer>> positions;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<List<Integer>> getPositions() {
        return positions;
    }

    public PaylineConfig(int id, String name, List<List<Integer>> positions) {
        this.id = id;
        this.name = name;
        this.positions = positions;
    }
}
