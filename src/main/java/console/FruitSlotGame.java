package console;

import domain.model.GameConfig;
import domain.model.GameSettings;
import domain.model.Payline;
import domain.model.Position;
import domain.model.Reel;
import domain.model.Symbol;
import infrastructure.SlotGameConfigProvider;

public class FruitSlotGame {
    public static void main(String[] args) {
        GameConfig config = new SlotGameConfigProvider().load();
        GameSettings game = config.game();

        System.out.println("Fruit Slot Game Configuration");
        System.out.println("============================");
        System.out.println("Rows: " + game.getRows());
        System.out.println("Reels: " + game.getReels());
        System.out.println("Paylines: " + game.getPaylines());
        System.out.println("Bet amount: " + game.getBetAmount());
        System.out.println("Simulation rounds: " + game.getSimulationRounds());

        System.out.println("\nSymbols (" + config.symbols().size() + "):");
        for (Symbol symbol : config.symbols()) {
            System.out.println("- " + symbol.getCode() + ": " + symbol.getName() + " (" + symbol.getSymbolType() + ")");
        }

        System.out.println("\nReel strips (" + config.reels().size() + "):");
        for (Reel reel : config.reels()) {
            System.out.println("- Reel " + reel.getId() + ": " + reel.getSymbols().size() + " symbols");
        }

        System.out.println("\nPaylines (" + config.paylines().size() + "):");
        for (Payline payline : config.paylines()) {
            System.out.print("- " + payline.getId() + " " + payline.getName() + ": ");
            for (Position position : payline.getPositions()) {
                System.out.print("[" + position.getRow() + ", " + position.getReel() + "] ");
            }
            System.out.println();
        }

        System.out.println("\nPaytable entries: " + config.paytable().getBaseGamePayouts().size());
        System.out.println("Bonus coin outcomes: " + config.bonusCoinTable().getCoinValues().size());
        System.out.println("Bonus dice outcomes: " + config.bonusCoinTable().getDiceValues().size());
        System.out.println("Wild symbol: " + config.wildRules().getWildSymbol().getCode());
        System.out.println("Bonus trigger symbol: " + config.bonusTriggerRules().getTriggerSymbol().getCode());
        System.out.println("Bonus trigger count: " + config.bonusTriggerRules().getMinimumVisibleCount());

        System.out.println("\nExpected RTP:");
        System.out.printf("- Base game: %.4f%%%n", config.expectedRtp().getBaseGame() * 100);
        System.out.printf("- Bonus game: %.4f%%%n", config.expectedRtp().getBonusGame() * 100);
        System.out.printf("- Total: %.4f%%%n", config.expectedRtp().getTotal() * 100);
    }
}
