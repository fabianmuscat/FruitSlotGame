package domain.model;

public enum SymbolType {
    WILD("wild"),
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low"),
    BONUS("bonus");

    private final String name;

    SymbolType(String name) {
        this.name = name;
    }
}
