package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TopPlayers extends Menu {

    public TopPlayers() throws IOException, FontFormatException {
        this.position = new TerminalPosition(15,1);
    }

    @Override
    public void scrollGui(int currentMenuIndex, Integer earlierIndex, String[] mainMenuOptions) throws IOException {

    }

    @Override
    public void initializeView(String[] mainMenuOptions) throws IOException {
        List<String> linesFromFile = Files.readAllLines(Paths.get("topPlayers.txt"));
        screen.clear();
        screen.refresh();
        int i = 0;
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
        for (String line : linesFromFile) {
            if (i == 10) break;
            textGraphics.putString(position.getColumn(), position.getRow() + i++, line, SGR.BOLD);
        }

        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
        textGraphics.putString(34, 16, "BACK", SGR.BOLD);
        screen.refresh();
    }

    public void arrowDownOrArrowUp() throws IOException {
        textGraphics.putString(34, 16, "BACK", SGR.REVERSE);
        screen.refresh();
    }
}
