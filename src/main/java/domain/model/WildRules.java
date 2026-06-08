package domain.model;

import java.util.List;

public class WildRules {
    private Symbol wildSymbol;
    private List<Symbol> substitutesFor;

    public Symbol getWildSymbol() {
        return wildSymbol;
    }

    public List<Symbol> getSubstitutesFor() {
        return substitutesFor;
    }

    public WildRules(Symbol wildSymbol, List<Symbol> substitutesFor) {
        this.wildSymbol = wildSymbol;
        this.substitutesFor = substitutesFor;
    }
}
