package controller.listeners;

import controller.utils.GameActivator;
import controller.utils.GameMechanicsUtils;
import controller.utils.OptionChanger;
import model.DataBase;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.PacManCurrentPosition;

import java.io.IOException;

public class PlayerInteractListener {
    public static void onPlayerInteract(DataBase data) throws IOException, InterruptedException {
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
                            data.getMainMenu().scrollGui(data.getCurrentMainMenuIndex(), null, data.getMainMenuOptions());
                            data.setCurrentMainMenuIndex(0, null);
                        } else {
                            data.getMainMenu().scrollGui(data.getCurrentMainMenuIndex() - 1, data.getCurrentMainMenuIndex(), data.getMainMenuOptions());
                            data.setCurrentMainMenuIndex(data.getCurrentMainMenuIndex() - 1, data.getCurrentMainMenuIndex());
                        }
                        break;
                    case ArrowDown:
                        if (data.getCurrentMainMenuIndex() != 3) {
                            data.getMainMenu().scrollGui(data.getCurrentMainMenuIndex() + 1, data.getCurrentMainMenuIndex(), data.getMainMenuOptions());
                            data.setCurrentMainMenuIndex(data.getCurrentMainMenuIndex() + 1, data.getCurrentMainMenuIndex());
                        }
                        break;
                    case Enter:
                        if (data.getCurrentMainMenuIndex() == 0) {
                            data.setInMainMenu(false);
                            data.getMainMenu().clear();
                            data.setInGame(true);
                            data.getGame().showCurrentName(data.getPlayerName());
                        } else if (data.getCurrentMainMenuIndex() == 1) {
                            data.setInMainMenu(false);
                            data.getMainMenu().clear();
                            data.setInSettings(true);
                            data.getMenuSettings().initializeView(data.getMenuSettingsOptions());
                            data.setCurrentSettingsMenuIndex(0, null);
                            data.getMenuSettings().scrollGui(data.getCurrentSettingsMenuIndex(), null, data.getMenuSettingsOptions());
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
                            data.getMenuSettings().scrollGui(data.getCurrentSettingsMenuIndex(), null, data.getMenuSettingsOptions());
                            data.setCurrentSettingsMenuIndex(0, null);
                        } else {
                            data.getMenuSettings().scrollGui(data.getCurrentSettingsMenuIndex() - 1, data.getCurrentSettingsMenuIndex(), data.getMenuSettingsOptions());
                            data.setCurrentSettingsMenuIndex(data.getCurrentSettingsMenuIndex() - 1, data.getCurrentSettingsMenuIndex());
                        }
                        break;
                    case ArrowDown:
                        if (data.getCurrentSettingsMenuIndex() != 3) {
                            data.getMenuSettings().scrollGui(data.getCurrentSettingsMenuIndex() + 1, data.getCurrentSettingsMenuIndex(), data.getMenuSettingsOptions());
                            data.setCurrentSettingsMenuIndex(data.getCurrentSettingsMenuIndex() + 1, data.getCurrentSettingsMenuIndex());
                        }
                        break;
                    case ArrowLeft:
                        if (data.getCurrentSettingsMenuIndex() == 0) {
                            data.toggleSound();
                        }
                        else if (data.getCurrentSettingsMenuIndex() == 1) {
                            data.setGameLevel(OptionChanger.newGameLevel(false, data.getGameLevel()));
                        } else if (data.getCurrentSettingsMenuIndex() == 2) {
                            data.setMapSize(OptionChanger.newMapSize(false, data.getMapSize()));
                        }

                        OptionChanger.updateSettings(data);
                        break;
                    case ArrowRight:
                        if (data.getCurrentSettingsMenuIndex() == 0) {
                            data.toggleSound();
                        }
                        else if (data.getCurrentSettingsMenuIndex() == 1) {
                            data.setGameLevel(OptionChanger.newGameLevel(true, data.getGameLevel()));
                        } else if (data.getCurrentSettingsMenuIndex() == 2) {
                            data.setMapSize(OptionChanger.newMapSize(true, data.getMapSize()));
                        }

                        OptionChanger.updateSettings(data);
                        break;
                    case Enter:
                        if (data.getCurrentSettingsMenuIndex() == 3) {
                            data.setInSettings(false);
                            data.getMenuSettings().clear();
                            data.setInMainMenu(true);
                            data.getMainMenu().initializeView(data.getMainMenuOptions());
                            data.setCurrentMainMenuIndex(0, null);
                            data.getMainMenu().scrollGui(data.getCurrentMainMenuIndex(), null, data.getMainMenuOptions());
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
                        if (data.isFirstClick()) {
                            data.getGame().clear();
                            data.setFirstClick(false);
                        }
                        data.getGame().showCurrentName(data.getPlayerName());
                        break;
                    case Backspace:
                        if (builder.length() > 0) builder.deleteCharAt(builder.length() - 1);
                        data.getGame().clear();
                        data.setPlayerName(builder.toString());
                        if (data.isFirstClick()) data.setFirstClick(false);
                        data.getGame().showCurrentName(data.getPlayerName());
                        break;
                    case Enter:
                        hasDefaultName = false;
                        break;
                }
            }
        }
    }


    public static void startGame(DataBase data) throws IOException, InterruptedException {
        data.setCurrentPoints(0);
        char[][] map = GameActivator.initMap(data);
        map[17][42] = 'C';
        PacManCurrentPosition pacManCurrentPosition = new PacManCurrentPosition(17, 42);
        data.setPacManCurrentPosition(pacManCurrentPosition);
        data.setMap(map);
        data.getGame().generateMap(map);
        while (data.isInGame()) {
            Terminal terminal = data.getTerminal();
            KeyStroke keyStroke = terminal.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        if (GameMechanicsUtils.canGoTo(pacManCurrentPosition.getRow() - 1, pacManCurrentPosition.getColumn(), map)) {
                            data.addPoints(GameMechanicsUtils.calculatePoints(pacManCurrentPosition.getRow() - 1, pacManCurrentPosition.getColumn(), map));
                            if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                map[pacManCurrentPosition.getRow() - 1][pacManCurrentPosition.getColumn()] = 'O';
                            } else {
                                if (DataBase.isLeftDirection()) map[pacManCurrentPosition.getRow() - 1][pacManCurrentPosition.getColumn()] = '☽';
                                else map[pacManCurrentPosition.getRow() - 1][pacManCurrentPosition.getColumn()] = '☾';
                            }

                            map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                            pacManCurrentPosition.setRow(pacManCurrentPosition.getRow() - 1);
                            data.setMap(map);
                            data.getGame().refreshMap(pacManCurrentPosition.getRow() + 1, pacManCurrentPosition.getColumn(), pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            Thread.sleep(100);
                        }
                        break;
                    case ArrowDown:
                        if (GameMechanicsUtils.canGoTo(pacManCurrentPosition.getRow() + 1, pacManCurrentPosition.getColumn(), map)) {
                            data.addPoints(GameMechanicsUtils.calculatePoints(pacManCurrentPosition.getRow() + 1, pacManCurrentPosition.getColumn(), map));
                            if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                map[pacManCurrentPosition.getRow() + 1][pacManCurrentPosition.getColumn()] = 'O';
                            } else {
                                if (DataBase.isLeftDirection()) map[pacManCurrentPosition.getRow() + 1][pacManCurrentPosition.getColumn()] = '☽';
                                else map[pacManCurrentPosition.getRow() + 1][pacManCurrentPosition.getColumn()] = '☾';
                            }

                            map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                            pacManCurrentPosition.setRow(pacManCurrentPosition.getRow() + 1);
                            data.setMap(map);
                            data.getGame().refreshMap(pacManCurrentPosition.getRow() - 1, pacManCurrentPosition.getColumn(), pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            Thread.sleep(100);
                        }

                        break;
                    case ArrowLeft:
                        DataBase.setIsLeftDirection(true);
                        if (GameMechanicsUtils.canGoTo(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn() - 2, map)) {
                            data.addPoints(GameMechanicsUtils.calculatePoints(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map));
                            if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn() - 2] = 'O';
                            } else {

                                map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn() - 2] = '☽';
                            }

                            map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                            pacManCurrentPosition.setColumn(pacManCurrentPosition.getColumn() - 2);
                            data.setMap(map);
                            data.getGame().refreshMap(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn() + 2, pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            Thread.sleep(100);
                        }

                        break;
                    case ArrowRight:
                        DataBase.setIsLeftDirection(false);
                        if (GameMechanicsUtils.canGoTo(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn() + 2, map)) {
                            data.addPoints(GameMechanicsUtils.calculatePoints(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map));
                            if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn() + 2] = 'O';
                            } else {
                                map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn() + 2] = '☾';
                            }

                            map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                            pacManCurrentPosition.setColumn(pacManCurrentPosition.getColumn() + 2);
                            data.setMap(map);
                            data.getGame().refreshMap(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn() - 2, pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            Thread.sleep(100);
                        }
                        break;
                }
            }
        }
    }

}
