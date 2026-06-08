package domain.dto;

import java.util.Map;

public class PaytableConfig {
    private Map<String, Integer> baseGamePayouts;

    public Map<String, Integer> getBaseGamePayouts() {
        return baseGamePayouts;
    }

    public PaytableConfig(Map<String, Integer> baseGamePayouts) {
        this.baseGamePayouts = baseGamePayouts;
    }
}
