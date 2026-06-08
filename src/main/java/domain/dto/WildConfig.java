package domain.dto;

import java.util.List;

public class WildConfig {
    private String symbol;
    private List<String> substitutesFor;

    public String getSymbol() {
        return symbol;
    }

    public List<String> getSubstitutesFor() {
        return substitutesFor;
    }

    public WildConfig(String symbol, List<String> substitutesFor) {
        this.symbol = symbol;
        this.substitutesFor = substitutesFor;
    }
}
