package domain.service;

import domain.model.*;

import java.util.List;
import java.util.Random;

public class BonusEvaluator {
    private BonusTriggerRules bonusTriggerRules;
    private BonusCoinTable bonusCoinTable;
    private int betAmount;
    private Random random;

    public BonusEvaluator(BonusTriggerRules bonusTriggerRules, BonusCoinTable bonusCoinTable, int betAmount, Random random) {
        this.bonusTriggerRules = bonusTriggerRules;
        this.bonusCoinTable = bonusCoinTable;
        this.betAmount = betAmount;
        this.random = random;
    }

    public BonusResult evaluate(SpinResult spinResult) {
        if (!isTriggered(spinResult)) {
            return new BonusResult(false, 0);
        }

        // Bonus win uses two separate weighted draws from the challenge data.
        int coinValue = selectWeightedValue(bonusCoinTable.getCoinValues());
        int diceMultiplier = selectWeightedValue(bonusCoinTable.getDiceValues());
        int winAmount = coinValue * diceMultiplier * betAmount;

        return new BonusResult(true, winAmount);
    }

    private boolean isTriggered(SpinResult spinResult) {
        int visibleCount = 0;

        // Scatter bonus symbols are counted anywhere in the visible 3x3 grid, not only on paylines.
        for (Symbol[] row : spinResult.getGrid()) {
            for (Symbol symbol : row) {
                if (symbol.getCode().equals(bonusTriggerRules.getTriggerSymbol().getCode())) {
                    visibleCount++;
                }
            }
        }

        return visibleCount >= bonusTriggerRules.getMinimumVisibleCount();
    }

    private int selectWeightedValue(List<WeightedValue> weightedValues) {
        int totalWeight = 0;
        for (WeightedValue weightedValue : weightedValues) {
            totalWeight += weightedValue.getWeight();
        }

        int draw = random.nextInt(totalWeight) + 1;
        int cumulativeWeight = 0;

        // Convert a random draw in the total weight range into the configured weighted outcome.
        for (WeightedValue weightedValue : weightedValues) {
            cumulativeWeight += weightedValue.getWeight();
            if (draw <= cumulativeWeight) {
                return weightedValue.getValue();
            }
        }

        throw new IllegalStateException("Weighted selection failed");
    }
}
