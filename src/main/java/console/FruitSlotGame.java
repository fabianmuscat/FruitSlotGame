package console;

import domain.model.GameConfig;
import infrastructure.SlotGameConfigProvider;

public class FruitSlotGame {
    public static void main(String[] args) {
        GameConfig config = new SlotGameConfigProvider().load();
        return;
    }
}
