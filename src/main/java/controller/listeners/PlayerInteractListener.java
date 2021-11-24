package controller.listeners;

import com.googlecode.lanterna.TextColor;
import controller.utils.GameActivator;
import controller.utils.GameMechanicsUtils;
import controller.utils.OptionChanger;
import controller.utils.Path;
import model.DataBase;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.Ghost;
import model.PacManCurrentPosition;

import java.io.IOException;
import java.util.List;

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
        char[][] mapElements = GameActivator.initMap(data);
        data.setMapElements(mapElements);

        map[17][42] = 'C';
        map[11][28] = 'G';
        map[11][26] = 'G';
        map[11][22] = 'G';
        PacManCurrentPosition pacManCurrentPosition = new PacManCurrentPosition(17, 42);
        data.setPacManCurrentPosition(pacManCurrentPosition);
        data.setMap(map);
        data.getGame().generateMap(map);


        Ghost ghost1 = new Ghost(TextColor.ANSI.GREEN, 11, 28);
        data.addGhost(ghost1);
        data.addAmountOfGhosts(ghost1.getRowPosition(), ghost1.getColPosition(), 1);
        Ghost ghost2 = new Ghost(TextColor.ANSI.RED, 11, 26);
        data.addGhost(ghost2);
        data.addAmountOfGhosts(ghost2.getRowPosition(), ghost2.getColPosition(), 1);
        Ghost ghost3 = new Ghost(TextColor.ANSI.MAGENTA_BRIGHT, 11, 22);
        data.addGhost(ghost3);
        data.addAmountOfGhosts(ghost3.getRowPosition(), ghost3.getColPosition(), 1);
        map[ghost1.getRowPosition()][ghost1.getColPosition()] = 'G';
        map[ghost2.getRowPosition()][ghost2.getColPosition()] = 'G';
        map[ghost3.getRowPosition()][ghost3.getColPosition()] = 'G';
        data.getGame().initializeGhost(ghost1.getRowPosition(), ghost1.getColPosition(), ghost1.getGhostColor(), map);
        data.getGame().initializeGhost(ghost2.getRowPosition(), ghost2.getColPosition(), ghost2.getGhostColor(), map);
        data.getGame().initializeGhost(ghost3.getRowPosition(), ghost3.getColPosition(), ghost3.getGhostColor(), map);
//        Ghost ghost2 = new Ghost(TextColor.ANSI.RED, 11, 26);
//        data.addGhost(ghost2);
//        Ghost ghost3 = new Ghost(TextColor.ANSI.GREEN, 11, 22);
//        data.addGhost(ghost3);
//        data.addAmountOfGhosts(ghost2.getRowPosition(), ghost1.getColPosition(), 1);
//        data.addAmountOfGhosts(ghost3.getRowPosition(), ghost1.getColPosition(), 1);
        while (data.isInGame()) {
            Terminal terminal = data.getTerminal();
            KeyStroke keyStroke = terminal.pollInput();
            if (keyStroke != null) {
                boolean isArrowPressed = false;
                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        if (GameMechanicsUtils.canGoTo(pacManCurrentPosition.getRow() - 1, pacManCurrentPosition.getColumn(), mapElements)) {
                            data.addPoints(GameMechanicsUtils.calculatePoints(pacManCurrentPosition.getRow() - 1, pacManCurrentPosition.getColumn(), map));
                            if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                map[pacManCurrentPosition.getRow() - 1][pacManCurrentPosition.getColumn()] = 'O';
                            } else {
                                if (DataBase.isLeftDirection()) map[pacManCurrentPosition.getRow() - 1][pacManCurrentPosition.getColumn()] = '☽';
                                else map[pacManCurrentPosition.getRow() - 1][pacManCurrentPosition.getColumn()] = '☾';
                            }

                            map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                            mapElements[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                            pacManCurrentPosition.setRow(pacManCurrentPosition.getRow() - 1);
                            data.getGame().refreshMap(pacManCurrentPosition.getRow() + 1, pacManCurrentPosition.getColumn(), pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            Thread.sleep(100);
                        }

                        isArrowPressed = true;
                        break;
                    case ArrowDown:
                        if (GameMechanicsUtils.canGoTo(pacManCurrentPosition.getRow() + 1, pacManCurrentPosition.getColumn(), map)) {
                            data.addPoints(GameMechanicsUtils.calculatePoints(pacManCurrentPosition.getRow() + 1, pacManCurrentPosition.getColumn(), mapElements));
                            if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                map[pacManCurrentPosition.getRow() + 1][pacManCurrentPosition.getColumn()] = 'O';
                            } else {
                                if (DataBase.isLeftDirection()) map[pacManCurrentPosition.getRow() + 1][pacManCurrentPosition.getColumn()] = '☽';
                                else map[pacManCurrentPosition.getRow() + 1][pacManCurrentPosition.getColumn()] = '☾';
                            }

                            map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                            mapElements[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                            pacManCurrentPosition.setRow(pacManCurrentPosition.getRow() + 1);

                            data.getGame().refreshMap(pacManCurrentPosition.getRow() - 1, pacManCurrentPosition.getColumn(), pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            Thread.sleep(100);
                        }

                        isArrowPressed = true;
                        break;
                    case ArrowLeft:
                        DataBase.setIsLeftDirection(true);
                        if (GameMechanicsUtils.canGoTo(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn() - 2, map)) {
                            data.addPoints(GameMechanicsUtils.calculatePoints(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), mapElements));
                            if (pacManCurrentPosition.getColumn() == 0) {
                                if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                    map[pacManCurrentPosition.getRow()][50] = 'O';
                                } else {

                                    map[pacManCurrentPosition.getRow()][50] = '☽';
                                }

                                map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                                mapElements[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                                pacManCurrentPosition.setColumn(50);
                                data.getGame().refreshMap(pacManCurrentPosition.getRow(), 0, pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            } else {
                                if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                    map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn() - 2] = 'O';
                                } else {

                                    map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn() - 2] = '☽';
                                }

                                map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                                mapElements[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                                pacManCurrentPosition.setColumn(pacManCurrentPosition.getColumn() - 2);
                                data.getGame().refreshMap(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn() + 2, pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            }


                            Thread.sleep(100);
                        }

                        isArrowPressed = true;
                        break;
                    case ArrowRight:
                        DataBase.setIsLeftDirection(false);
                        if (GameMechanicsUtils.canGoTo(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn() + 2, map)) {
                            data.addPoints(GameMechanicsUtils.calculatePoints(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), mapElements));
                            if (pacManCurrentPosition.getColumn() == 50) {
                                if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                    map[pacManCurrentPosition.getRow()][0] = 'O';
                                } else {
                                    map[pacManCurrentPosition.getRow()][0] = '☾';
                                }

                                map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                                mapElements[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                                pacManCurrentPosition.setColumn(0);
                                data.getGame().refreshMap(pacManCurrentPosition.getRow(), 50, pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            } else {
                                if (map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] != 'O') {
                                    map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn() + 2] = 'O';
                                } else {
                                    map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn() + 2] = '☾';
                                }

                                map[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                                mapElements[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] = ' ';
                                pacManCurrentPosition.setColumn(pacManCurrentPosition.getColumn() + 2);
                                data.getGame().refreshMap(pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn() - 2, pacManCurrentPosition.getRow(), pacManCurrentPosition.getColumn(), map);
                            }

                            Thread.sleep(100);
                        }

                        if (data.getAmountOfGhosts()[pacManCurrentPosition.getRow()][pacManCurrentPosition.getColumn()] > 0) {
                            PlayerInteractListener.gameOver(data);
                            return;
                        }

                        isArrowPressed = true;
                        break;
                }

                if (isArrowPressed) {
                    List<Ghost> ghosts = data.getGhosts();
                    for (Ghost ghost : ghosts) {
                        Path path = GameMechanicsUtils.choosePath(data.getMapElements(), ghost.getRowPosition(), ghost.getColPosition(), ghost.getLatestPath());
                        System.out.println("wybrana droga to " + path);
                        switch (path) {
                            case TOP:
                                System.out.println("moge isc do gory");
                                if (ghost.getColPosition() == pacManCurrentPosition.getColumn() && (ghost.getRowPosition() == pacManCurrentPosition.getRow() || ghost.getRowPosition() - 1 == pacManCurrentPosition.getRow())) {
                                    PlayerInteractListener.gameOver(data);
                                    return;
                                }

                                map[ghost.getRowPosition() - 1][ghost.getColPosition()] = 'G';
                                map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
                                data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
                                data.addAmountOfGhosts(ghost.getRowPosition() - 1, ghost.getColPosition(), 1);

                                System.out.println(data.getMapElements()[ghost.getRowPosition()][ghost.getColPosition()]);
                                System.out.println(data.getAmountOfGhosts()[ghost.getRowPosition()][ghost.getColPosition()]);
                                    data.getGame().updateGhost(ghost.getRowPosition(), ghost.getColPosition(), ghost.getRowPosition() - 1,
                                            ghost.getColPosition(), data.getMap(), ghost.getGhostColor(),
                                            data.getMapElements()[ghost.getRowPosition()][ghost.getColPosition()]);

                                ghost.setRowPosition(ghost.getRowPosition() - 1);
                                break;
                            case BOTTOM:
                                System.out.println("moge isc do dolu");
                                if (ghost.getColPosition() == pacManCurrentPosition.getColumn() && (ghost.getRowPosition() == pacManCurrentPosition.getRow() || ghost.getRowPosition() + 1 == pacManCurrentPosition.getRow())) {
                                    PlayerInteractListener.gameOver(data);
                                    return;
                                }

                                map[ghost.getRowPosition() + 1][ghost.getColPosition()] = 'G';
                                map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
                                data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
                                data.addAmountOfGhosts(ghost.getRowPosition() + 1, ghost.getColPosition(), 1);

                                System.out.println(data.getMapElements()[ghost.getRowPosition()][ghost.getColPosition()]);
                                System.out.println(data.getAmountOfGhosts()[ghost.getRowPosition()][ghost.getColPosition()]);
                                    System.out.println("tuwchodzimy");
                                    data.getGame().updateGhost(ghost.getRowPosition(), ghost.getColPosition(), ghost.getRowPosition() + 1,
                                            ghost.getColPosition(), data.getMap(), ghost.getGhostColor(),
                                            data.getMapElements()[ghost.getRowPosition()][ghost.getColPosition()]);


                                ghost.setRowPosition(ghost.getRowPosition() + 1);
                                break;
                            case LEFT:

                                System.out.println("moge isc w lewo");
                                if ((ghost.getColPosition() == pacManCurrentPosition.getColumn() || ghost.getColPosition() - 2 == pacManCurrentPosition.getColumn()) && ghost.getRowPosition() == pacManCurrentPosition.getRow()) {
                                    PlayerInteractListener.gameOver(data);
                                    return;
                                }

                                if (ghost.getColPosition() >= 2) {
                                    map[ghost.getRowPosition()][ghost.getColPosition() - 2] = 'G';
                                    map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
                                    data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
                                    data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition() - 2, 1);

                                    System.out.println(data.getAmountOfGhosts()[ghost.getRowPosition()][ghost.getColPosition()]);
                                    System.out.println(data.getMapElements()[ghost.getRowPosition()][ghost.getColPosition()]);
                                        System.out.println("tuwchodzimy");
                                        data.getGame().updateGhost(ghost.getRowPosition(), ghost.getColPosition(), ghost.getRowPosition(),
                                                ghost.getColPosition() - 2, data.getMap(), ghost.getGhostColor(),
                                                data.getMapElements()[ghost.getRowPosition()][ghost.getColPosition()]);


                                    ghost.setColPosition(ghost.getColPosition() - 2);
                                } else if (ghost.getColPosition() == 0) {
                                    map[ghost.getRowPosition()][50] = 'G';
                                    map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
                                    data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
                                    data.addAmountOfGhosts(ghost.getRowPosition(), 50, 1);
                                    data.getGame().updateGhost(ghost.getRowPosition(), ghost.getColPosition(), ghost.getRowPosition(),
                                            50, data.getMap(), ghost.getGhostColor(),
                                            data.getMapElements()[ghost.getRowPosition()][ghost.getColPosition()]);

                                    ghost.setColPosition(50);
                                }

                                break;
                            case RIGHT:
                                System.out.println("moge isc w prawo");
                                if ((ghost.getColPosition() == pacManCurrentPosition.getColumn() || ghost.getColPosition() + 2 == pacManCurrentPosition.getColumn()) && ghost.getRowPosition() == pacManCurrentPosition.getRow()) {
                                    PlayerInteractListener.gameOver(data);
                                    return;
                                }
                                if (ghost.getColPosition() <= 48) {
                                    map[ghost.getRowPosition()][ghost.getColPosition() + 2] = 'G';
                                    map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
                                    data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
                                    data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition() + 2, 1);
                                        data.getGame().updateGhost(ghost.getRowPosition(), ghost.getColPosition(), ghost.getRowPosition(),
                                                ghost.getColPosition() + 2, data.getMap(), ghost.getGhostColor(),
                                                data.getMapElements()[ghost.getRowPosition()][ghost.getColPosition()]);

                                    ghost.setColPosition(ghost.getColPosition() + 2);
                                } else if (ghost.getColPosition() == 50) {
                                    map[ghost.getRowPosition()][0] = 'G';
                                    map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
                                    data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
                                    data.addAmountOfGhosts(ghost.getRowPosition(), 0, 1);
                                    data.getGame().updateGhost(ghost.getRowPosition(), ghost.getColPosition(), ghost.getRowPosition(),
                                            0, data.getMap(), ghost.getGhostColor(),
                                            data.getMapElements()[ghost.getRowPosition()][ghost.getColPosition()]);

                                    ghost.setColPosition(0);
                                }

                                break;
                        }

                        ghost.setLatestPath(path);
                    }
                }
            }
        }
    }

    public static void printMap(DataBase data, char[][] test) {
        for (int i = 0; i < data.getMapRows(); i++) {
            for (int j = 0; j < data.getMapColumns(); j++) {
                System.out.print(test[i][j]);
            }

            System.out.println();
        }
    }

    public static void gameOver(DataBase data) throws IOException {
        data.getGame().gameOver();

        KeyStroke keyStroke = null;
        while (keyStroke == null) {
            data.setInGame(false);
            data.setInMainMenu(true);
            Terminal terminal = data.getTerminal();
            keyStroke = terminal.pollInput();
            if (keyStroke != null) {
            }
        }

    }
}
