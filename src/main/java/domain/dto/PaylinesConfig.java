package domain.dto;

import java.util.List;

public class PaylinesConfig {
    private String coordinateConvention;
    private List<PaylineConfig> lines;

    public String getCoordinateConvention() {
        return coordinateConvention;
    }

    public List<PaylineConfig> getLines() {
        return lines;
    }

    public PaylinesConfig(String coordinateConvention, List<PaylineConfig> lines) {
        this.coordinateConvention = coordinateConvention;
        this.lines = lines;
    }
}
