package Model;

import View.Menu;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class DataBase {
    private final String[] menuOptions = {
            "START",
            "SETTINGS",
            "TOP PLAYERS",
            "EXIT"
    };

    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    private Terminal terminal = defaultTerminalFactory.createTerminal();
    private Screen screen = new TerminalScreen(terminal);
    private boolean isInMenu = true;
    private int currentMenuIndex = 0;
    private Menu menu;

    public DataBase() throws IOException {
    }

    public void initializeMenu(Menu menu) {
        this.menu = menu;
    }
    public String[] getMenuOptions() {
        return menuOptions;
    }

    public boolean isInMenu() {
        return isInMenu;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getCurrentMenuIndex() {
        return currentMenuIndex;
    }

    public void setCurrentMenuIndex(int i, Integer earlierIndex) throws IOException {
        currentMenuIndex = i;
        menu.updateTextGraphic(currentMenuIndex, earlierIndex);
    }
}

