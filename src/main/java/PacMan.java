import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import controller.listeners.PlayerInteractListener;
import model.DataBase;
import view.Game;
import view.MainMenu;
import view.Menu;
import view.Settings;

import java.awt.*;
import java.io.IOException;

public class PacMan {
    public static void onEnable() throws IOException, InterruptedException, FontFormatException {


        DataBase data = new DataBase();
        Terminal terminal = data.getTerminal();
        Screen screen = data.getScreen();
        screen.startScreen();
        screen.setCursorPosition(null);
        TextGraphics textGraphics = data.getTextGraphics();
        MainMenu mainMenu = new MainMenu(terminal, screen, textGraphics, data.getMainMenuOptions());
        Settings menuSettings = new Settings(terminal, screen, textGraphics);
        Game game = new Game(terminal, screen, textGraphics);

        data.initializeMainMenu(mainMenu);
        data.initializeSettingsMenu(menuSettings);
        data.initializeGame(game);

        while (true) {
            PlayerInteractListener.onPlayerInteract(data);
        }

    }
}
