import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenBuffer;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;
import controller.listeners.PlayerInteractListener;
import model.DataBase;
import view.Game;
import view.MainMenu;
import view.Menu;
import view.Settings;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PacMan {
    public static void onEnable() throws IOException, InterruptedException, FontFormatException {

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal =  defaultTerminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
        TextGraphics textGraphics = screen.newTextGraphics();
        DataBase data = new DataBase(terminal, screen, textGraphics);
        MainMenu mainMenu = new MainMenu(terminal, screen, textGraphics, data.getMainMenuOptions());
        Settings menuSettings = new Settings(terminal, screen, textGraphics);
        Game game = new Game(terminal, screen, textGraphics);
        data.setMainMenu(mainMenu);
        data.setSettingsMenu(menuSettings);
        data.setGame(game);

        while (true) {
            PlayerInteractListener.onPlayerInteract(data);
        }

    }
}
