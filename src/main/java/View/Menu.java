package View;

import Model.DataBase;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Screen screen;
    private Terminal terminal;
    private DataBase data;
    TerminalPosition position = new TerminalPosition(34,8);
    private Map<Integer, Integer> rowForIndex = new HashMap<>();
    TextGraphics textGraphics;

    public Menu(DataBase data) throws IOException, FontFormatException {
        this.screen = data.getScreen();
        this.terminal = data.getTerminal();
        this.data = data;
        if (textGraphics == null) textGraphics = screen.newTextGraphics();
        int i = position.getRow();
        int y = 0;
        for (String menuOption : data.getMenuOptions()) {
            rowForIndex.put(y++, i);
            textGraphics.putString(position.getColumn(), i++, menuOption);
        }

        screen.refresh();
    }

    public void updateTextGraphic(int currentMenuIndex, Integer earlierIndex) throws IOException {
        if (earlierIndex != null) textGraphics.putString(position.getColumn(), rowForIndex.get(earlierIndex), data.getMenuOptions()[earlierIndex], SGR.BORDERED);
        textGraphics.putString(position.getColumn(), rowForIndex.get(currentMenuIndex), data.getMenuOptions()[currentMenuIndex], SGR.REVERSE);
        screen.refresh();
    }

}
