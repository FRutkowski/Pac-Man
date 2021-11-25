package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.DataBase;

import java.awt.*;
import java.io.IOException;

public class MainMenu extends Menu {

    public MainMenu(Terminal terminal, Screen screen, TextGraphics textGraphics, String[] mainMenuOptions) throws IOException, FontFormatException {
        super(terminal, screen, textGraphics);
        initializeView(mainMenuOptions);
    }

    @Override
    public void scrollGui(int currentMenuIndex, Integer earlierIndex, String[] mainMenuOptions) throws IOException {
        if (earlierIndex != null) textGraphics.putString(position.getColumn(), rowForIndex.get(earlierIndex), mainMenuOptions[earlierIndex], SGR.BOLD);
        textGraphics.putString(position.getColumn(), rowForIndex.get(currentMenuIndex), mainMenuOptions[currentMenuIndex], SGR.REVERSE);
        screen.refresh();
    }

    @Override
    public void initializeView(String[] mainMenuOptions) throws IOException {
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
        int i = position.getRow();
        int y = 0;
        for (String menuOption : mainMenuOptions) {
            if (!initializedRowForIndex) rowForIndex.put(y++, i);
            textGraphics.putString(position.getColumn(), i++, menuOption, SGR.BOLD);
        }

        initializedRowForIndex = true;
        screen.refresh();
    }


}
