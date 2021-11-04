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
    private Terminal terminal;
    private Screen screen;
    private TextGraphics textGraphics;
    private boolean isInMainMenu = true;
    private boolean isInSettings = false;
    private boolean isInGame = false;
    private boolean isSoundOn = true;
    private String gameLevel = "MEDIUM";
    private String mapSize = "MEDIUM";
    private int mapColumns = 51;
    private int mapRows = 23;
    private int currentMainMenuIndex = 0;
    private int currentSettingsMenuIndex = 0;
    private MainMenu menu;
    private Settings menuSettings;
    private Game game;
    private String playerName = "Player";
    public boolean firstClick = true;

    public DataBase(Terminal terminal, Screen screen, TextGraphics textGraphic) throws IOException {
        this.terminal = terminal;
        this.screen = screen;
        this.textGraphics = textGraphic;

    }

    public void setMainMenu(MainMenu menu) {
        this.menu = menu;
    }

    public MainMenu getMainMenu() {
        return menu;
    }

    public void setSettingsMenu(Settings menuSettings) {
        this.menuSettings = menuSettings;
    }

    public Settings getMenuSettings() {
        return menuSettings;
    }

    public void setGame(Game g) {
        game = g;
    }

    public Game getGame() {
        return game;
    }

    public String[] getMainMenuOptions() {
        return mainMenuOptions;
    }

    public boolean isInMainMenu() {
        return isInMainMenu;
    }

    public void setInMainMenu(boolean inMainMenu) throws IOException {
        isInMainMenu = inMainMenu;
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
    }

    public void setInSettings(boolean inSettings) throws IOException {
        isInSettings = inSettings;
    }

    public boolean isInSettings() {
        return isInSettings;
    }

    public void setInGame(boolean inGame) throws IOException {
        isInGame = inGame;
    }

    public int getCurrentSettingsMenuIndex() {
        return currentSettingsMenuIndex;
    }

    public void setCurrentSettingsMenuIndex(int currentSettingsMenuIndex, Integer earlierIndex) throws IOException {
        this.currentSettingsMenuIndex = currentSettingsMenuIndex;
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

    }

    public String getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(String gameLevel) throws IOException {
        this.gameLevel = gameLevel;
        menuSettingsOptions[1] = "LEVEL: " + gameLevel;

    }

    public String getMapSize() {
        return mapSize;
    }

    public void setMapSize(String mapSize) throws IOException {
        this.mapSize = mapSize;
        menuSettingsOptions[2] = "MAP-SIZE: " + mapSize;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) throws IOException {
        this.playerName = playerName;
    }

    public void setFirstClick(boolean firstClick) {
        this.firstClick = firstClick;
    }

    public boolean isFirstClick() {
        return firstClick;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public int getMapColumns() {
        return mapColumns;
    }

    public void setMapColumns(int mapColumns) {
        this.mapColumns = mapColumns;
    }

    public int getMapRows() {
        return mapRows;
    }

    public void setMapRows(int mapRows) {
        this.mapRows = mapRows;
    }
}

