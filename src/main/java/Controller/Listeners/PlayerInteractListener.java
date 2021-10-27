package Controller.Listeners;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.table.TableModel;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class PlayerInteractListener {
    public static void onPlayerInteract(Screen screen) {
        try {
            boolean isRunning = true;
            while (isRunning) {
                Terminal terminal = null;
                KeyStroke keyPressed = terminal.pollInput();

                if (keyPressed != null) {
                    switch (keyPressed.getKeyType()) {
                        case ArrowDown:

                            break;
                        case ArrowUp:
                            break;
                        case ArrowLeft:
                            break;
                        case ArrowRight:
                            break;
                        case Character:
                            Character character = keyPressed.getCharacter();
                            if (character.toString().toUpperCase().equals("W")) {

                            }

                            if (character.toString().toUpperCase().equals("A")) {

                            }

                            if (character.toString().toUpperCase().equals("S")) {

                            }

                            if (character.toString().toUpperCase().equals("D")) {

                            }
                            break;
                        case Escape:
                            isRunning = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
