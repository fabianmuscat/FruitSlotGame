package domain.service;

import domain.model.*;

import java.util.List;

public class PaylineEvaluator {
    private List<Payline> paylines;
    private Paytable paytable;
    private WildRules wildRules;

    public PaylineEvaluator(List<Payline> paylines, Paytable paytable, WildRules wildRules) {
        this.paylines = paylines;
        this.paytable = paytable;
        this.wildRules = wildRules;
    }

    public int evaluate(SpinResult spinResult) {
        int totalWin = 0;

        // A round can win on multiple paylines, so each configured line is evaluated independently.
        for (Payline payline : paylines) {
            totalWin += evaluatePayline(spinResult.getGrid(), payline);
        }

        return totalWin;
    }

    private int evaluatePayline(Symbol[][] grid, Payline payline) {
        Symbol targetSymbol = null;
        boolean allWilds = true;

        for (Position position : payline.getPositions()) {
            Symbol symbol = grid[position.getRow()][position.getReel()];

            // Bonus symbols do not participate in base-game line wins.
            if (symbol.getSymbolType() == SymbolType.BONUS) {
                return 0;
            }

            // Wilds are ignored while finding the real symbol they should represent.
            if (symbol.getCode().equals(wildRules.getWildSymbol().getCode())) {
                continue;
            }

            allWilds = false;

            if (!canWildSubstituteFor(symbol)) {
                return 0;
            }

            // All non-wild symbols on the line must match for the wilds to complete a win.
            if (targetSymbol == null) {
                targetSymbol = symbol;
            } else if (!targetSymbol.getCode().equals(symbol.getCode())) {
                return 0;
            }
        }

        // If the line contains only wild symbols, it pays as a wild combination.
        if (allWilds) {
            targetSymbol = wildRules.getWildSymbol();
        }

        if (targetSymbol == null) {
            return 0;
        }

        return paytable.getPayoutFor(targetSymbol);
    }

    private boolean canWildSubstituteFor(Symbol symbol) {
        for (Symbol substituteSymbol : wildRules.getSubstitutesFor()) {
            if (substituteSymbol.getCode().equals(symbol.getCode())) {
                return true;
            }
        }

        return false;
    }
}
