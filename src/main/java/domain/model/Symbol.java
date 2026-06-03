package domain.model;

public class Symbol {
    private String code;
    private String name;
    private SymbolType symbolType;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public Symbol(String code, String name, SymbolType symbolType) {
        this.code = code;
        this.name = name;
        this.symbolType = symbolType;
    }
}
