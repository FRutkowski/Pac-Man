import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import controller.listeners.PlayerInteractListener;
import model.DataBase;
import view.Game;
import view.MainMenu;
import view.Settings;
import view.TopPlayers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class PacMan extends JPanel {
    public static void main(String[] args) throws IOException, InterruptedException, FontFormatException {
        JFrame frame = new JFrame("Pac-Man");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        DataBase data = new DataBase();
        PacMan pacMan = new PacMan(data);

        pacMan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent event) {
                super.keyPressed(event);
                try {
                    PlayerInteractListener.onPlayerInteract(data, event);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

    }

    public PacMan(DataBase data) throws IOException, FontFormatException {
        MainMenu mainMenu = new MainMenu(data.getMainMenuOptions());
        Settings menuSettings = new Settings();
        TopPlayers topPlayers = new TopPlayers();
        Game game = new Game();
        data.setMainMenu(mainMenu);
        data.setSettingsMenu(menuSettings);
        data.setTopPlayers(topPlayers);
        data.setGame(game);
    }
}
