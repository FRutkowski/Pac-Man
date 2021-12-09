package view;

import model.DataBase;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Menu {
    protected Screen screen;
    protected Terminal terminal;
    TerminalPosition position = new TerminalPosition(34,8);
    protected Map<Integer, Integer> rowForIndex = new HashMap<>();
    protected boolean initializedRowForIndex = false;
    TextGraphics textGraphics = null;

    public Menu() {

    }

    public abstract void scrollGui(int currentMenuIndex, Integer earlierIndex, String[] mainMenuOptions) throws IOException;

    public void clear() throws IOException {
        screen.clear();
        screen.refresh();
    }

    public abstract void initializeView(String[] mainMenuOptions) throws IOException;

}
