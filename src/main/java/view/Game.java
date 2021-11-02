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
    private char[][] map;
    TextGraphics textGraphics;

    public Game(Terminal terminal, Screen screen, TextGraphics textGraphics) {
        this.terminal = terminal;
        this.screen = screen;
        if (this.textGraphics == null) this.textGraphics = textGraphics;
    }


    public void clear() throws IOException {
        screen.clear();
        screen.refresh();
    }

    public void showCurrentName(String playerName) throws IOException {
        textGraphics.putString(34, 8, "Name: " + playerName);
        screen.refresh();
    }

    public void generateMap() {

    }

}
