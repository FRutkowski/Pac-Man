package model;

import com.googlecode.lanterna.graphics.TextGraphics;
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

    private final String[] menuSettingsOptions = {
            "SOUND: OFF",
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
    private boolean isSoundOn = true;
    private String gameLevel = "MEDIUM";
    private String mapSize = "MEDIUM";
    private int currentMainMenuIndex = 0;
    private int currentSettingsMenuIndex = 0;
    private MainMenu menu;
    private Settings menuSettings;

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

    public void setSoundOn(boolean soundOn) {
        isSoundOn = soundOn;
    }

    public String getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(String gameLevel) {
        this.gameLevel = gameLevel;
    }

    public String getMapSize() {
        return mapSize;
    }

    public void setMapSize(String mapSize) {
        this.mapSize = mapSize;
    }
}

