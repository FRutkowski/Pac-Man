package Controller.Listeners;

import Model.DataBase;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.table.TableModel;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class PlayerInteractListener {
    public static void onPlayerInteract(DataBase data) throws IOException {
        if (data.isInMenu()) {
            manageMenu(data);
        }

    }


    public static void manageMenu(DataBase data) throws IOException {
        while (data.isInMenu()) {
            Terminal terminal = data.getTerminal();
            Screen screen = data.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        if (data.getCurrentMenuIndex() == 0) {
                            data.setCurrentMenuIndex(0, null);
                        } else {
                            data.setCurrentMenuIndex(data.getCurrentMenuIndex() - 1, data.getCurrentMenuIndex());
                        }
                        break;
                    case ArrowDown:
                        if (data.getCurrentMenuIndex() != 3) data.setCurrentMenuIndex(data.getCurrentMenuIndex() + 1, data.getCurrentMenuIndex());
                        break;
                    case ArrowLeft:
                        break;
                    case ArrowRight:
                        break;
                }
            }
        }
    }
}
