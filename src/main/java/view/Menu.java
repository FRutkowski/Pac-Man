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
    protected DataBase data;
    TerminalPosition position = new TerminalPosition(34,8);
    protected Map<Integer, Integer> rowForIndex = new HashMap<>();
    protected boolean initializedRowForIndex = false;
    TextGraphics textGraphics;

    public Menu(DataBase data) throws IOException, FontFormatException {
        this.screen = data.getScreen();
        this.terminal = data.getTerminal();
        this.data = data;
        if (textGraphics == null) textGraphics = data.getTextGraphics();

    }

    public abstract void scrollGui(int currentMenuIndex, Integer earlierIndex) throws IOException;

    public void clear() throws IOException {
        screen.clear();
        screen.refresh();
    }

    public abstract void initializeView() throws IOException;

}
