package domain.dto;

import java.util.List;

public class PaylinesConfig {
    private List<PaylineConfig> lines;

    public List<PaylineConfig> getLines() {
        return lines;
    }

    public PaylinesConfig(List<PaylineConfig> lines) {
        this.lines = lines;
    }
}
