package infrastructure;

import domain.model.GameConfig;
import domain.model.Payline;
import domain.model.Paytable;
import domain.model.Reel;

import java.util.List;

public class SlotGameConfigProvider {
    public GameConfig load() {
        return new GameConfig(createReels(),
                createPaylines(),
                createPaytable()
        );
    }

    private List<Reel> createReels() {
        throw new UnsupportedOperationException();
    }

    private List<Payline> createPaylines() {
        throw new UnsupportedOperationException();
    }

    private Paytable createPaytable() {
        throw new UnsupportedOperationException();
    }
}
