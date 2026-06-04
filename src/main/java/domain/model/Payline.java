package domain.model;

import java.util.List;

public class Payline {
    private int id;
    private String name;
    private List<Position> positions;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Payline(int id, String name, List<Position> positions) {
        this.id = id;
        this.name = name;
        this.positions = positions;
    }
}

