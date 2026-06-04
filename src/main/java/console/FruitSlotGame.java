package console;

import domain.model.*;
import domain.service.ReelSpinner;
import infrastructure.SlotGameConfigProvider;

import java.util.Arrays;

public class FruitSlotGame {
    public static void main(String[] args) {
        GameConfig config = new SlotGameConfigProvider().load();

        ReelSpinner spinner = new ReelSpinner(config.reels(), config.game().getRows());
        SpinResult spin = spinner.spin();

        System.out.println("Reel stops: " + Arrays.toString(spin.getReelStops()));
        System.out.println("Visible grid:");

        for (Symbol[] row : spin.getGrid()) {
            for (Symbol symbol : row) {
                System.out.printf("%-4s", symbol.getCode());
            }
            System.out.println();
        }
    }
}
