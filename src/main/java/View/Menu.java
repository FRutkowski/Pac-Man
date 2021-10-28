package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Menu {
    private Screen screen;
    private Terminal terminal;
    TerminalPosition position = new TerminalPosition(34,8);
    TextGraphics textGraphics;
    private final String[] menuOptions = {
            "START",
            "SETTINGS",
            "TOP PLAYERS",
            "EXIT"
    };

    public Menu(Screen screen, Terminal terminal) throws IOException {
        this.screen = screen;
        this.terminal = terminal;
        textGraphics = screen.newTextGraphics();
        int i = position.getRow();
        for (String menuOption : menuOptions) {
            textGraphics.putString(position.getColumn(), i++, menuOption);
        }


        screen.refresh();
    }
}
