package console;

import application.SimulationResult;
import application.SimulationRunner;
import domain.model.*;
import domain.service.BonusEvaluator;
import domain.service.PaylineEvaluator;
import domain.service.ReelSpinner;
import infrastructure.SlotGameConfigProvider;

import java.util.Arrays;
import java.util.Random;

public class FruitSlotGame {
    public static void main(String[] args) {
        Random random = new Random();
        GameConfig config = new SlotGameConfigProvider().load();
        SimulationResult result = getSimulationResult(config, random);

        long totalWin = result.getBaseGameWin() + result.getBonusGameWin();
        double baseGameRtp = (double) result.getBaseGameWin() / result.getTotalBet();
        double bonusGameRtp = (double) result.getBonusGameWin() / result.getTotalBet();
        double totalRtp = (double) totalWin / result.getTotalBet();
        double bonusTriggerFrequency = (double) result.getBonusTriggers() / result.getRounds();
        double baseGameHitFrequency = (double) result.getBaseGameHitRounds() / result.getRounds();

        System.out.println("Fruit Slot Game Simulation");
        System.out.println("==========================");
        System.out.println("Rounds simulated: " + result.getRounds());
        System.out.println("Total bet: " + result.getTotalBet());
        System.out.println("Total base-game win: " + result.getBaseGameWin());
        System.out.println("Total bonus-game win: " + result.getBonusGameWin());
        System.out.println("Total win: " + totalWin);
        System.out.println("==========================");
        System.out.println("Bonus triggers: " + result.getBonusTriggers());
        System.out.printf("Bonus trigger frequency: %.6f%%%n", bonusTriggerFrequency * 100);
        System.out.println("==========================");
        System.out.println("Base-game hit rounds: " + result.getBaseGameHitRounds());
        System.out.printf("Base-game hit frequency: %.6f%%%n", baseGameHitFrequency * 100);
        System.out.println("==========================");

        System.out.println();
        System.out.printf("Base-game RTP: %.4f%% (expected %.4f%%)%n",
                baseGameRtp * 100,
                config.expectedRtp().getBaseGame() * 100
        );
        System.out.printf("Bonus-game RTP: %.4f%% (expected %.4f%%)%n",
                bonusGameRtp * 100,
                config.expectedRtp().getBonusGame() * 100
        );
        System.out.printf("Total RTP: %.4f%% (expected %.4f%%)%n",
                totalRtp * 100,
                config.expectedRtp().getTotal() * 100
        );
    }

    private static SimulationResult getSimulationResult(GameConfig config, Random random) {
        ReelSpinner spinner = new ReelSpinner(config.reels(), config.game().getRows());

        PaylineEvaluator paylineEvaluator = new PaylineEvaluator(config.paylines(), config.paytable(), config.wildRules());
        BonusEvaluator bonusEvaluator = new BonusEvaluator(config.bonusTriggerRules(), config.bonusCoinTable(), config.game().getBetAmount(), random);

        SimulationRunner simulationRunner = new SimulationRunner(config, spinner, paylineEvaluator, bonusEvaluator);
        SimulationResult result = simulationRunner.run();
        return result;
    }
}
