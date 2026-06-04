package infrastructure;

import com.google.gson.Gson;
import domain.dto.*;
import domain.model.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SlotGameConfigProvider {
    public GameConfig load() {
        GameConfigFile configFile = readConfigFile();
        return convertToGameConfig(configFile);
    }

    private GameConfigFile readConfigFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("game-config.json");

        if (inputStream == null)
            throw new IllegalStateException("Could not find game-config.json");

        InputStreamReader reader = new InputStreamReader(inputStream);
        return new Gson().fromJson(reader, GameConfigFile.class);
    }

    private GameConfig convertToGameConfig(GameConfigFile configFile) {
        GameSettings gameSettings = convertGameSettings(configFile.getGame());

        List<Symbol> symbols = convertSymbols(configFile.getSymbols());
        Map<String, Symbol> symbolsByCode = symbols.stream().collect(Collectors.toMap(Symbol::getCode, symbol -> symbol));

        List<Reel> reels = convertReels(configFile.getReels(), symbolsByCode, gameSettings);
        List<Payline> paylines = convertPaylines(configFile.getPaylines(), gameSettings);
        Paytable paytable = convertPaytable(configFile.getPaytable(), symbolsByCode);
        BonusCoinTable bonusCoinTable = convertBonusCoinTable(configFile.getBonusGame());
        WildRules wildRules = convertWildRulesConfig(configFile.getWild(), symbolsByCode);
        BonusTriggerRules bonusTriggerRules = convertBonusTriggerRules(configFile.getBonusGame().getTrigger(), symbolsByCode, gameSettings);
        ExpectedRtp expectedRtp = convertExpectedRtp(configFile.getExpectedRtp());

        return new GameConfig(
                gameSettings,
                symbols,
                reels,
                paylines,
                paytable,
                bonusCoinTable,
                wildRules,
                bonusTriggerRules,
                expectedRtp
        );
    }

    private GameSettings convertGameSettings(GameSettingsConfig gameSettingsConfig) {
        return new GameSettings(
                gameSettingsConfig.getRows(),
                gameSettingsConfig.getReels(),
                gameSettingsConfig.getPaylines(),
                gameSettingsConfig.getBetAmount(),
                gameSettingsConfig.getSimulationRounds()
        );
    }

    private List<Symbol> convertSymbols(List<SymbolConfig> symbolConfig) {
        List<Symbol> symbols = new ArrayList<>();
        for (SymbolConfig sym : symbolConfig) {
            symbols.add(new Symbol(sym.getCode(), sym.getName(), SymbolType.valueOf(sym.getType().toUpperCase())));
        }

        return symbols;
    }

    private List<Reel> convertReels(List<ReelConfig> reelConfig, Map<String, Symbol> symbols, GameSettings gameSettings) {
        List<Reel> reels = new ArrayList<>();

        for (ReelConfig rs : reelConfig) {
            if (rs.getSymbols() == null || rs.getSymbols().isEmpty()) {
                throw new IllegalStateException("Reels must have a symbol");
            }

            List<Symbol> rsSymbols = new ArrayList<>();

            for (String symbolCode : rs.getSymbols()) {
                Symbol symbol = symbols.get(symbolCode);
                if (symbol == null) {
                    throw new IllegalStateException("Unknown symbol " + symbolCode);
                }
                rsSymbols.add(symbol);
            }

            reels.add(new Reel(rs.getId(), rsSymbols));
        }

        if (reels.size() != gameSettings.getReels()) {
            throw new IllegalStateException("Reels must be equal to " + gameSettings.getReels());
        }

        return reels;
    }

    private List<Payline> convertPaylines(PaylinesConfig paylineConfig, GameSettings gameSettings) {
        List<PaylineConfig> lines = paylineConfig.getLines();

        if (lines.size() != gameSettings.getPaylines()) {
            throw new IllegalArgumentException(
                    "Expected " + gameSettings.getPaylines() + " paylines but found " + lines.size()
            );
        }

        List<Payline> paylines = new ArrayList<>();

        for (PaylineConfig line : lines) {
            List<Position> positions = new ArrayList<>();
            if (line.getPositions().size() != gameSettings.getReels()) {
                throw new IllegalArgumentException("Payline " + line.getId() + " must have " + gameSettings.getReels() + " positions");
            }

            for (List<Integer> ps : line.getPositions()) {
                if (ps.size() != 2) {
                    throw new IllegalStateException("Each payline position must contain a row and reel index");
                }

                int row = ps.get(0);
                int reel = ps.get(1);

                if (row < 0 || row >= gameSettings.getRows()) {
                    throw new IllegalStateException("Row index must be between 0 and " + (gameSettings.getRows() - 1));
                }

                if (reel < 0 || reel >= gameSettings.getReels()) {
                    throw new IllegalStateException("Reel index must be between 0 and " + (gameSettings.getReels() - 1));
                }

                positions.add(new Position(row, reel));
            }
            paylines.add(new Payline(line.getId(), line.getName(), positions));
        }

        return paylines;
    }

    private Paytable convertPaytable(PaytableConfig paytableConfig, Map<String, Symbol> symbols) {
        Paytable paytable = new Paytable();

        for (Map.Entry<String, Integer> btPayout : paytableConfig.getBaseGamePayouts().entrySet()) {
            Symbol symbol = symbols.get(btPayout.getKey());
            if (symbol == null) {
                throw new IllegalStateException("Unknown symbol: " + btPayout.getKey());
            }

            if (btPayout.getValue() < 0) {
                throw new IllegalStateException("Payout value must be greater than or equal to zero");
            }

            paytable.addBaseGamePayout(symbol, btPayout.getValue());
        }

        return paytable;
    }

    private BonusCoinTable convertBonusCoinTable(BonusGameConfig bonusGameConfig) {
        List<WeightedValueConfig> coinTableConfig = bonusGameConfig.getCoinTable();
        List<WeightedValueConfig> diceTableConfig = bonusGameConfig.getDiceTable();

        if (coinTableConfig == null || coinTableConfig.isEmpty()) {
            throw new IllegalStateException("Coin table config is empty");
        }

        if (diceTableConfig == null || diceTableConfig.isEmpty()) {
            throw new IllegalStateException("Dice table config is empty");
        }

        BonusCoinTable bonusCoinTable = new BonusCoinTable();

        for (WeightedValueConfig coin : coinTableConfig) {
            if (coin.getWeight() <= 0 || coin.getValue() <= 0) {
                throw new IllegalStateException("Invalid coin data: " + coin.getValue() + " " + coin.getWeight());
            }

            bonusCoinTable.addCoinValue(new WeightedValue(coin.getWeight(), coin.getValue()));
        }

        for (WeightedValueConfig dice: diceTableConfig) {
            if  (dice.getWeight() <= 0 || dice.getMultiplier() <= 0) {
                throw new IllegalStateException("Invalid dice data: " + dice.getWeight() + " " + dice.getMultiplier());
            }

            bonusCoinTable.addDiceValue(new WeightedValue(dice.getWeight(), dice.getMultiplier()));
        }

        return bonusCoinTable;
    }

    private WildRules convertWildRulesConfig(WildConfig wildConfig, Map<String, Symbol> symbols) {
        Symbol wildSymbol = symbols.get(wildConfig.getSymbol());
        if (wildSymbol == null) {
            throw new IllegalStateException("Unknown symbol: " + wildConfig.getSymbol());
        }

        List<String> subsFor = wildConfig.getSubstitutesFor();
        if (subsFor == null || subsFor.isEmpty()) {
            throw new IllegalStateException("Substitutes for list is empty");
        }

        List<String> notSubsFor = wildConfig.getDoesNotSubstituteFor();
        if (notSubsFor == null || notSubsFor.isEmpty()) {
            throw new IllegalStateException("Does not substitute for list is empty");
        }

        List<Symbol> substitutesFor = new ArrayList<>();
        for (String symbolCode : subsFor) {
            Symbol symbol = symbols.get(symbolCode);
            if (symbol == null) {
                throw new IllegalStateException("Unknown symbol: " + symbolCode);
            }
            substitutesFor.add(symbol);
        }

        List<Symbol> notSubstitutesFor = new ArrayList<>();
        for (String symbolCode : notSubsFor) {
            Symbol symbol = symbols.get(symbolCode);
            if (symbol == null) {
                throw new IllegalStateException("Unknown symbol: " + symbolCode);
            }
            notSubstitutesFor.add(symbol);
        }

        return new WildRules(wildSymbol,  substitutesFor, notSubstitutesFor);
    }

    private BonusTriggerRules convertBonusTriggerRules(BonusTriggerConfig bonusTriggerConfig, Map<String, Symbol> symbols, GameSettings gameSettings) {
        Symbol triggerSymbol = symbols.get(bonusTriggerConfig.getSymbol());
        if (triggerSymbol == null) {
            throw new IllegalStateException("Unknown symbol: " + bonusTriggerConfig.getSymbol());
        }

        if (triggerSymbol.getSymbolType() != SymbolType.BONUS) {
            throw new IllegalStateException("Unknown symbol type: " + triggerSymbol.getSymbolType());
        }

        if (bonusTriggerConfig.getMinimumVisibleCount() <= 0 || bonusTriggerConfig.getMinimumVisibleCount() > gameSettings.getRows() * gameSettings.getReels()) {
            throw new IllegalStateException("Minimum visible count must be greater than or equal to zero");
        }

        return new BonusTriggerRules(triggerSymbol, bonusTriggerConfig.getMinimumVisibleCount());
    }

    private ExpectedRtp convertExpectedRtp(ExpectedRtpConfig expectedRtpConfig) {
        return new ExpectedRtp(
                expectedRtpConfig.getBaseGame(),
                expectedRtpConfig.getBonusGame(),
                expectedRtpConfig.getTotal()
        );
    }
}
