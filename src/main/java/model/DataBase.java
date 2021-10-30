package model;

import com.googlecode.lanterna.graphics.TextGraphics;
import view.Game;
import view.MainMenu;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import view.Settings;

import java.io.IOException;

public class DataBase {
    private final String[] mainMenuOptions = {
            "START",
            "SETTINGS",
            "TOP PLAYERS",
            "EXIT"
    };

    private String[] menuSettingsOptions = {
            "SOUND: ON",
            "LEVEL: MEDIUM",
            "MAP-SIZE: MEDIUM",
            "BACK"
    };


    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    private Terminal terminal = defaultTerminalFactory.createTerminal();
    private Screen screen = new TerminalScreen(terminal);
    private TextGraphics textGraphics = screen.newTextGraphics();
    private boolean isInMainMenu = true;
    private boolean isInSettings = false;
    private boolean isInGame = false;
    private boolean isSoundOn = true;
    private String gameLevel = "MEDIUM";
    private String mapSize = "MEDIUM";
    private int currentMainMenuIndex = 0;
    private int currentSettingsMenuIndex = 0;
    private MainMenu menu;
    private Settings menuSettings;
    private Game game;
    private String playerName = "Player";

    public DataBase() throws IOException {
    }

    public void initializeMainMenu(MainMenu menu) {
        this.menu = menu;
    }

    public void initializeSettingsMenu(Settings menuSettings) {
        this.menuSettings = menuSettings;
    }

    public String[] getMainMenuOptions() {
        return mainMenuOptions;
    }

    public boolean isInMainMenu() {
        return isInMainMenu;
    }

    public void setInMainMenu(boolean inMainMenu) throws IOException {
        isInMainMenu = inMainMenu;
        if (!inMainMenu) menu.clear();
        else menu.initializeView();
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getCurrentMainMenuIndex() {
        return currentMainMenuIndex;
    }

    public void setCurrentMainMenuIndex(int i, Integer earlierIndex) throws IOException {
        currentMainMenuIndex = i;
        menu.scrollGui(currentMainMenuIndex, earlierIndex);
    }

    public void setInSettings(boolean inSettings) throws IOException {
        isInSettings = inSettings;
        if (!inSettings) menuSettings.clear();
        else menuSettings.initializeView();
    }

    public boolean isInSettings() {
        return isInSettings;
    }

    public void setInGame(boolean inGame) throws IOException {
        isInGame = inGame;
        if (inGame) game.showCurrentName();
        else game.clear();
    }

    public int getCurrentSettingsMenuIndex() {
        return currentSettingsMenuIndex;
    }

    public void setCurrentSettingsMenuIndex(int currentSettingsMenuIndex, Integer earlierIndex) throws IOException {
        this.currentSettingsMenuIndex = currentSettingsMenuIndex;
        menuSettings.scrollGui(currentSettingsMenuIndex, earlierIndex);
    }

    public String[] getMenuSettingsOptions() {
        return menuSettingsOptions;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public boolean isSoundOn() {
        return isSoundOn;
    }

    public void toggleSound() throws IOException {
        if (isSoundOn) {
            isSoundOn = false;
            menuSettingsOptions[0] = "SOUND: OFF";
        } else {
            isSoundOn = true;
            menuSettingsOptions[0] = "SOUND: ON";
        }

        menuSettings.clear();
        menuSettings.initializeView();
        menuSettings.scrollGui(currentSettingsMenuIndex, null);
    }

    public String getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(String gameLevel) throws IOException {
        this.gameLevel = gameLevel;
        menuSettingsOptions[1] = "LEVEL: " + gameLevel;
        menuSettings.clear();
        menuSettings.initializeView();
        menuSettings.scrollGui(currentSettingsMenuIndex, null);

    }

    public String getMapSize() {
        return mapSize;
    }

    public void setMapSize(String mapSize) throws IOException {
        this.mapSize = mapSize;
        menuSettingsOptions[2] = "MAP-SIZE: " + mapSize;
        menuSettings.clear();
        screen.clear();
        menuSettings.initializeView();
        menuSettings.scrollGui(currentSettingsMenuIndex, null);

    }

    public void initializeGame(Game g) {
        game = g;
    }

    public Game getGame() {
        return game;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) throws IOException {
        this.playerName = playerName;
        game.clear();
        game.showCurrentName();

    }

    public boolean isInGame() {
        return isInGame;
    }
}

