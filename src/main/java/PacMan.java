import Controller.Listeners.PlayerInteractListener;
import Model.DataBase;
import View.Menu;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;

public class PacMan {
    public static void onEnable() throws IOException, InterruptedException, FontFormatException {


        DataBase data = new DataBase();
        data.getScreen().startScreen();
        data.getScreen().setCursorPosition(null);
        Menu menu = new Menu(data);
        data.initializeMenu(menu);

        PlayerInteractListener.onPlayerInteract(data);

    }
}
