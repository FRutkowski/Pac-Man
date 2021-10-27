import Controller.Listeners.PlayerInteractListener;
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

import java.io.IOException;

public class PacMan {
    public static void onEnable() throws IOException, InterruptedException {

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = defaultTerminalFactory.createScreen();
        screen.startScreen();
        WindowBasedTextGUI textGui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLACK));

        Window window = new BasicWindow();
        TerminalPosition position = new TerminalPosition(3, 3);
        window.setPosition(position);
        Panel contentPanel = new Panel(new GridLayout(4));


        GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
        window.setPosition(position);
        gridLayout.setHorizontalSpacing(3);

//        contentPanel.addComponent(new TextBox().setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.CENTER)));
        Button playButton = new Button("PLAY", new Runnable() {
            @Override
            public void run() {

            }
        });
        playButton.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.CENTER,
                GridLayout.Alignment.CENTER,
                true,
                false,
                2,
                1 ));
        playButton.setLabel("Play");
//        contentPanel.addComponent(title);
        contentPanel.addComponent(playButton);
        window.setComponent(contentPanel);
        textGui.addWindowAndWait(window);
//        PlayerInteractListener.onPlayerInteract(screen);
    }
}
