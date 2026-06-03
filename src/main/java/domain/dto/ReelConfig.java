package domain.dto;

import java.util.List;

public class ReelConfig {
    private int id;
    private List<String> symbols;

    public int getId() {
        return id;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public ReelConfig(int id, List<String> symbols) {
        this.id = id;
        this.symbols = symbols;
    }
}
