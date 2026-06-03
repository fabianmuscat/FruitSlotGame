package domain.model;

public class Symbol {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Symbol(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
