package domain.model;

import java.util.List;

public class WildRules {
    private Symbol wildSymbol;
    private List<Symbol> substitutesFor;
    private List<Symbol> doesNotSubstituteFor;

    public Symbol getWildSymbol() {
        return wildSymbol;
    }

    public List<Symbol> getSubstitutesFor() {
        return substitutesFor;
    }

    public List<Symbol> getDoesNotSubstituteFor() {
        return doesNotSubstituteFor;
    }

    public WildRules(Symbol wildSymbol, List<Symbol> substitutesFor, List<Symbol> doesNotSubstituteFor) {
        this.wildSymbol = wildSymbol;
        this.substitutesFor = substitutesFor;
        this.doesNotSubstituteFor = doesNotSubstituteFor;
    }
}
