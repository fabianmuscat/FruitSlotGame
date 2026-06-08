package domain.model;

import java.util.List;

public class Payline {
    private List<Position> positions;

    public List<Position> getPositions() {
        return positions;
    }

    public Payline(List<Position> positions) {
        this.positions = positions;
    }
}
