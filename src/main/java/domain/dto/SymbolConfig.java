package domain.dto;

public class SymbolConfig {
    private String code;
    private String type;

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public SymbolConfig(String code, String type) {
        this.code = code;
        this.type = type;
    }
}
