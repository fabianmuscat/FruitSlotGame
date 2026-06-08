package domain.dto;

import java.util.List;

public class GameConfigFile {
    private GameSettingsConfig game;
    private List<SymbolConfig> symbols;
    private WildConfig wild;
    private List<ReelConfig> reels;
    private PaylinesConfig paylines;
    private PaytableConfig paytable;
    private BonusGameConfig bonusGame;

    public GameSettingsConfig getGame() {
        return game;
    }

    public List<SymbolConfig> getSymbols() {
        return symbols;
    }

    public WildConfig getWild() {
        return wild;
    }

    public List<ReelConfig> getReels() {
        return reels;
    }

    public PaylinesConfig getPaylines() {
        return paylines;
    }

    public PaytableConfig getPaytable() {
        return paytable;
    }

    public BonusGameConfig getBonusGame() {
        return bonusGame;
    }

}
