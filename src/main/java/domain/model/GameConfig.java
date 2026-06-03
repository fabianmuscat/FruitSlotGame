package domain.model;

import domain.dto.ReelConfig;

import java.util.List;

public record GameConfig(
        GameSettings game,
        List<Symbol> symbols,
        List<Reel> reels,
        List<Payline> paylines,
        Paytable paytable,
        BonusCoinTable bonusCoinTable,
        ExpectedRtp expectedRtp
) {
}
