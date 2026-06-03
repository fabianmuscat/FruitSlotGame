package domain.dto;

public class SymbolConfig {
    private String code;
    private String name;
    private String type;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public SymbolConfig(String code, String name, String type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }
}
