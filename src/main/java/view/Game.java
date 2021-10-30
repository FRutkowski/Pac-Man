package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.DataBase;

import java.io.IOException;

public class Game {
    private Terminal terminal;
    private Screen screen;
    private DataBase data;
    private char[][] map;
    TextGraphics textGraphics;

    public Game(DataBase data) {
        this.data = data;
        this.terminal = data.getTerminal();
        this.screen = data.getScreen();
        if (textGraphics == null) textGraphics = data.getTextGraphics();

    }


    public void clear() throws IOException {
        screen.clear();
        screen.refresh();
    }

    public void showCurrentName() throws IOException {
        textGraphics.putString(34, 8, "Name: " + data.getPlayerName());
        screen.refresh();
    }

}
