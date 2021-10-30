package controller.listeners;

import controller.utils.OptionChanger;
import model.DataBase;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class PlayerInteractListener {
    public static void onPlayerInteract(DataBase data) throws IOException {
        if (data.isInMainMenu()) {
            manageMainMenu(data);
        }

        if (data.isInSettings()) {
            manageSettingsMenu(data);
        }

        if (data.isInGame()) {
            pollPlayerName(data);
            startGame(data);
        }

    }


    public static void manageMainMenu(DataBase data) throws IOException {
        while (data.isInMainMenu()) {
            Terminal terminal = data.getTerminal();
            Screen screen = data.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        if (data.getCurrentMainMenuIndex() == 0) {
                            data.setCurrentMainMenuIndex(0, null);
                        } else {
                            data.setCurrentMainMenuIndex(data.getCurrentMainMenuIndex() - 1, data.getCurrentMainMenuIndex());
                        }
                        break;
                    case ArrowDown:
                        if (data.getCurrentMainMenuIndex() != 3) data.setCurrentMainMenuIndex(data.getCurrentMainMenuIndex() + 1, data.getCurrentMainMenuIndex());
                        break;
                    case Enter:
                        if (data.getCurrentMainMenuIndex() == 0) {
                            data.setInMainMenu(false);
                            data.setInGame(true);
                        } else if (data.getCurrentMainMenuIndex() == 1) {
                            data.setInMainMenu(false);
                            data.setInSettings(true);
                            data.setCurrentSettingsMenuIndex(0, null);
                        }
                        break;
                }
            }
        }
    }

    public static void manageSettingsMenu(DataBase data) throws IOException {
        while (data.isInSettings()) {
            Terminal terminal = data.getTerminal();
            Screen screen = data.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        if (data.getCurrentSettingsMenuIndex() == 0) {
                            data.setCurrentSettingsMenuIndex(0, null);
                        } else {
                            data.setCurrentSettingsMenuIndex(data.getCurrentSettingsMenuIndex() - 1, data.getCurrentSettingsMenuIndex());
                        }
                        break;
                    case ArrowDown:
                        if (data.getCurrentSettingsMenuIndex() != 3) data.setCurrentSettingsMenuIndex(data.getCurrentSettingsMenuIndex() + 1, data.getCurrentSettingsMenuIndex());
                        break;
                    case ArrowLeft:
                        if (data.getCurrentSettingsMenuIndex() == 0) data.toggleSound();
                        else if (data.getCurrentSettingsMenuIndex() == 1) {
                            data.setGameLevel(OptionChanger.newGameLevel(false, data.getGameLevel()));
                        } else if (data.getCurrentSettingsMenuIndex() == 2) {
                            data.setMapSize(OptionChanger.newMapSize(false, data.getMapSize()));
                        }

                        break;
                    case ArrowRight:
                        if (data.getCurrentSettingsMenuIndex() == 0) data.toggleSound();
                        else if (data.getCurrentSettingsMenuIndex() == 1) {
                            data.setGameLevel(OptionChanger.newGameLevel(true, data.getGameLevel()));
                        } else if (data.getCurrentSettingsMenuIndex() == 2) {
                            data.setMapSize(OptionChanger.newMapSize(true, data.getMapSize()));
                        }

                        break;
                    case Enter:
                        if (data.getCurrentSettingsMenuIndex() == 3) {
                            data.setInSettings(false);
                            data.setInMainMenu(true);
                            data.setCurrentMainMenuIndex(0, null);
                        }

                        break;
                }
            }
        }
    }

    public static void pollPlayerName(DataBase data) throws IOException {
        StringBuilder builder = new StringBuilder("");
        boolean hasDefaultName = true;
        while (hasDefaultName) {
            Terminal terminal = data.getTerminal();
            KeyStroke keyStroke = terminal.pollInput();

            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case Character:
                        builder.append(keyStroke.getCharacter());
                        data.setPlayerName(builder.toString());
                        break;
                    case Backspace:
                        if (builder.length() > 0) builder.deleteCharAt(builder.length() - 1);
                        data.setPlayerName(builder.toString());
                        break;
                    case Enter:
                        hasDefaultName = false;
                        break;
                }
            }
        }
    }


    public static void startGame(DataBase data) {
        while (data.isInGame()) {

        }
    }

}
