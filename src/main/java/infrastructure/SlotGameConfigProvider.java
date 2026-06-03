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
        List<Reel> reels = convertReels(configFile.getReels(), symbols);
        List<Payline> paylines = convertPaylines(configFile.getPaylines());
        Paytable paytable = convertPaytable(configFile.getPaytable());
        BonusCoinTable bonusCoinTable = convertBonusCoinTable(configFile.getBonusGame());
        ExpectedRtp expectedRtp = convertExpectedRtp(configFile.getExpectedRtp());

        return new GameConfig(
                gameSettings,
                symbols,
                reels,
                paylines,
                paytable,
                bonusCoinTable,
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

    private List<Reel> convertReels(List<ReelConfig> reelConfig, List<Symbol> symbols) {
        List<Reel> reels = new ArrayList<>();
        Map<String, Symbol> symbolsByCode = symbols.stream().collect(Collectors.toMap(Symbol::getCode, symbol -> symbol));

        for (ReelConfig rs : reelConfig) {
            List<Symbol> rsSymbols = new ArrayList<>();

            for (String symbolCode : rs.getSymbols()) {
                rsSymbols.add(symbolsByCode.get(symbolCode));
            }

            reels.add(new Reel(rs.getId(), rsSymbols));
        }

        return reels;
    }

    private List<Payline> convertPaylines(PaylinesConfig paylineConfig) {
        return null;
    }

    private Paytable convertPaytable(PaytableConfig paytableConfig) {
        return null;
    }

    private BonusCoinTable convertBonusCoinTable(BonusGameConfig bonusGameConfig) {
        return null;
    }

    private ExpectedRtp convertExpectedRtp(ExpectedRtpConfig expectedRtpConfig) {
        return new ExpectedRtp(
                expectedRtpConfig.getBaseGame(),
                expectedRtpConfig.getBonusGame(),
                expectedRtpConfig.getTotal()
        );
    }
}
