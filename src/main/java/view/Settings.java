package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.DataBase;

import java.awt.*;
import java.io.IOException;

public class Settings extends Menu {

    public Settings() throws IOException, FontFormatException {
    }

    @Override
    public void scrollGui(int currentMenuIndex, Integer earlierIndex, String[] mainMenuOptions) throws IOException {
        if (earlierIndex != null) textGraphics.putString(position.getColumn(), rowForIndex.get(earlierIndex), mainMenuOptions[earlierIndex], SGR.BOLD);
        textGraphics.putString(position.getColumn(), rowForIndex.get(currentMenuIndex), mainMenuOptions[currentMenuIndex], SGR.REVERSE);
        screen.refresh();
    }

    @Override
    public void initializeView(String[] mainMenuOptions) throws IOException {
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
