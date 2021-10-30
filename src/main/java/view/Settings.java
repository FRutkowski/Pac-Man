package view;

import com.googlecode.lanterna.SGR;
import model.DataBase;

import java.awt.*;
import java.io.IOException;

public class Settings extends Menu {

    public Settings(DataBase data) throws IOException, FontFormatException {
        super(data);
    }

    @Override
    public void scrollGui(int currentMenuIndex, Integer earlierIndex) throws IOException {
        if (earlierIndex != null) textGraphics.putString(position.getColumn(), rowForIndex.get(earlierIndex), data.getMenuSettingsOptions()[earlierIndex], SGR.BORDERED);
        textGraphics.putString(position.getColumn(), rowForIndex.get(currentMenuIndex), data.getMenuSettingsOptions()[currentMenuIndex], SGR.REVERSE);
        screen.refresh();
    }

    @Override
    public void initializeView() throws IOException {
        screen.clear();
        int i = position.getRow();
        int y = 0;
        for (String menuOption : data.getMenuSettingsOptions()) {
            if (!initializedRowForIndex) rowForIndex.put(y++, i);
            textGraphics.putString(position.getColumn(), i++, menuOption);
        }

        initializedRowForIndex = true;
        screen.refresh();
    }

}
