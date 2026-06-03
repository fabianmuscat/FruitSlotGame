package domain.model;

import java.util.List;

public record GameConfig(
        List<Reel> reels,
        List<Payline> paylines,
        Paytable paytable,
        BonusCoinTable bonusCoinTable,
        ExpectedRtp expectedRtp
) {
}
