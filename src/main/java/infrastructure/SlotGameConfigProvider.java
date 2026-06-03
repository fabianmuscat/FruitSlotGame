package infrastructure;

import domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class SlotGameConfigProvider {
    public GameConfig load() {
        return new GameConfig(createReels(),
                createPaylines(),
                createPaytable(),
                createBonusCoinTable(),
                createExpectedRtp()
        );
    }

    private ArrayList<Reel> createReels() {
        return new ArrayList<>() {{
            add(new Reel(new Symbol("W1", "Wild")));
            add(new Reel(new Symbol("H1", "Seven ")));
            add(new Reel(new Symbol("H2", "Bell")));
            add(new Reel(new Symbol("H3", "Bar")));
        }};
    }

    private List<Payline> createPaylines() {
        throw new UnsupportedOperationException();
    }

    private Paytable createPaytable() {
        throw new UnsupportedOperationException();
    }

    private BonusCoinTable createBonusCoinTable() {
        throw new UnsupportedOperationException();
    }

    private ExpectedRtp createExpectedRtp() {
        throw new UnsupportedOperationException();
    }
}
