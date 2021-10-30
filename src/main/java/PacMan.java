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
        data.getScreen().startScreen();
        data.getScreen().setCursorPosition(null);
        MainMenu mainMenu = new MainMenu(data);
        Settings menuSettings = new Settings(data);
        Game game = new Game(data);

        data.initializeMainMenu(mainMenu);
        data.initializeSettingsMenu(menuSettings);
        data.initializeGame(game);

        while (true) {
            PlayerInteractListener.onPlayerInteract(data);
        }

    }
}
