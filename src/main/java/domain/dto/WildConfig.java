package domain.dto;

import java.util.List;

public class WildConfig {
    private String symbol;
    private List<String> substitutesFor;
    private List<String> doesNotSubstituteFor;

    public String getSymbol() {
        return symbol;
    }

    public List<String> getSubstitutesFor() {
        return substitutesFor;
    }

    public List<String> getDoesNotSubstituteFor() {
        return doesNotSubstituteFor;
    }

    public WildConfig(String symbol, List<String> substitutesFor, List<String> doesNotSubstituteFor) {
        this.symbol = symbol;
        this.substitutesFor = substitutesFor;
        this.doesNotSubstituteFor = doesNotSubstituteFor;
    }
}
