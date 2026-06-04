package domain.model;

import java.util.List;

public class Reel {
    private int id;
    private List<Symbol> symbols;

    public int getId() {
        return id;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public Reel(int id, List<Symbol> symbols) {
        this.id = id;
        this.symbols = symbols;
    }

    public int getReelLength() {
        return symbols.size();
    }

    public Symbol getSymbolAt(int index) {
        return symbols.get(index % symbols.size());
    }
}
