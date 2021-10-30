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


}
