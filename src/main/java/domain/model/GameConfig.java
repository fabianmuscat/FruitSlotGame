package domain.model;

import java.util.List;

public record GameConfig(
        GameSettings game,
        List<Symbol> symbols,
        List<Reel> reels,
        List<Payline> paylines,
        Paytable paytable,
        BonusCoinTable bonusCoinTable,
        WildRules wildRules,
        BonusTriggerRules bonusTriggerRules,
        ExpectedRtp expectedRtp
) {
}
