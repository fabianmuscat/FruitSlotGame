package application;

import domain.model.BonusResult;
import domain.model.GameConfig;
import domain.model.SpinResult;
import domain.service.BonusEvaluator;
import domain.service.PaylineEvaluator;
import domain.service.ReelSpinner;

public class SimulationRunner {
    private final GameConfig config;
    private final ReelSpinner reelSpinner;
    private final PaylineEvaluator paylineEvaluator;
    private final BonusEvaluator bonusEvaluator;

    public SimulationRunner(GameConfig config, ReelSpinner reelSpinner, PaylineEvaluator paylineEvaluator, BonusEvaluator bonusEvaluator) {
        this.config = config;
        this.reelSpinner = reelSpinner;
        this.paylineEvaluator = paylineEvaluator;
        this.bonusEvaluator = bonusEvaluator;
    }

    public SimulationResult run() {
        long simulationRounds = config.game().getSimulationRounds();
        long totalBet = simulationRounds * config.game().getBetAmount();
        long totalBaseWinTotal = 0;
        long totalBonusWinTotal = 0;
        long baseHitRounds = 0;
        long bonusTriggers = 0;

        // Run the configured number of independent rounds and accumulate the totals used for RTP.
        for (long round = 0; round < simulationRounds; round++) {
            SpinResult spinResult = reelSpinner.spin();
            int baseGameWin = paylineEvaluator.evaluate(spinResult);
            BonusResult bonusResult = bonusEvaluator.evaluate(spinResult);

            totalBaseWinTotal += baseGameWin;
            totalBonusWinTotal += bonusResult.getWinAmount();

            if (baseGameWin > 0) {
                baseHitRounds++;
            }

            if (bonusResult.isTriggered()) {
                bonusTriggers++;
            }
        }

        return new SimulationResult(
                simulationRounds,
                totalBet,
                totalBaseWinTotal,
                totalBonusWinTotal,
                bonusTriggers,
                baseHitRounds
        );
    }
}
