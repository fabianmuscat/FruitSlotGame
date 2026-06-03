package domain.model;

import java.util.List;

public class Reel {
    private int id;
    private List<Symbol> symbols;

    public Reel(int id, List<Symbol> symbols) {
        this.id = id;
        this.symbols = symbols;
    }
}

