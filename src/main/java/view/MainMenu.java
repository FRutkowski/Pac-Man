package view;

import com.googlecode.lanterna.SGR;
import model.DataBase;

import java.awt.*;
import java.io.IOException;

public class MainMenu extends Menu {

    public MainMenu(DataBase data) throws IOException, FontFormatException {
        super(data);
        initializeView();
    }

    @Override
    public void scrollGui(int currentMenuIndex, Integer earlierIndex) throws IOException {
        if (earlierIndex != null) textGraphics.putString(position.getColumn(), rowForIndex.get(earlierIndex), data.getMainMenuOptions()[earlierIndex], SGR.BORDERED);
        textGraphics.putString(position.getColumn(), rowForIndex.get(currentMenuIndex), data.getMainMenuOptions()[currentMenuIndex], SGR.REVERSE);
        screen.refresh();
    }

    @Override
    public void initializeView() throws IOException {
        screen.clear();
        int i = position.getRow();
        int y = 0;
        for (String menuOption : data.getMainMenuOptions()) {
            if (!initializedRowForIndex) rowForIndex.put(y++, i);
            textGraphics.putString(position.getColumn(), i++, menuOption);
        }

        initializedRowForIndex = true;
        screen.refresh();
    }


}
