package domain.model;

public class Symbol {
    private String code;
    private SymbolType symbolType;

    public String getCode() {
        return code;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public Symbol(String code, SymbolType symbolType) {
        this.code = code;
        this.symbolType = symbolType;
    }
}
