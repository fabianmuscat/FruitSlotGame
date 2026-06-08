package domain.dto;

import java.util.List;

public class PaylineConfig {
    private List<List<Integer>> positions;

    public List<List<Integer>> getPositions() {
        return positions;
    }

    public PaylineConfig(List<List<Integer>> positions) {
        this.positions = positions;
    }
}
