package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.DataBase;
import model.PacManCurrentPosition;

import javax.xml.soap.Text;
import java.io.IOException;

public class Game {
    private Terminal terminal;
    private Screen screen;
    private char[][] map;
    private TextGraphics textGraphics;
    private TerminalPosition position = new TerminalPosition(15, 1);

    public Game() {

    }


    public void clear() throws IOException {
        screen.clear();
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
        screen.refresh();
    }

    public void showCurrentName(String playerName) throws IOException {
        textGraphics.putString(34, 8, "Name: " + playerName, SGR.BOLD);
        screen.refresh();
    }

    public void generateMap(char[][] map) throws IOException {
        screen.clear();
        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 51; x++) {
                if (map[y][x] != '*') {
                    if (map[y][x] == '▅' || map[y][x] == '▉') {
                        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
                        textGraphics.putString(position.getColumn() + x, position.getRow() + y, String.valueOf(map[y][x]), SGR.FRAKTUR);
                    } else if (map[y][x] == 'C') {
                        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
                        textGraphics.putString(position.getColumn() + x, position.getRow() + y, String.valueOf(map[y][x]), SGR.BOLD);
                    } else if (map[y][x] == '⍤') {
                        textGraphics.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
                        textGraphics.putString(position.getColumn() + x, position.getRow() + y, String.valueOf(map[y][x]), SGR.BOLD);
                    } else {
                        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
                        textGraphics.putString(position.getColumn() + x, position.getRow() + y, String.valueOf(map[y][x]));
                    }
                }
            }
        }

        screen.refresh();
    }

    public void updateGhost(int oldRow, int oldCol, int newRow, int newCol, char[][] map, TextColor.ANSI color, char currentField) throws IOException {
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
//        if (currentField == '━') textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(position.getColumn() + oldCol, position.getRow() + oldRow, String.valueOf(currentField));
        textGraphics.setForegroundColor(color);
        textGraphics.putString(position.getColumn() + newCol, position.getRow() + newRow, String.valueOf(map[newRow][newCol]), SGR.BOLD);
        screen.refresh();
    }

    public void refreshMap(int oldRow, int oldCol, int newRow, int newCol, char[][] map) throws IOException {
        if (map[newRow][newCol] == '☾' || map[newRow][newCol] == 'O' || map[newRow][newCol] == '☽' || map[newRow][newCol] == '◠') {
            textGraphics.putString(position.getColumn() + oldCol, position.getRow() + oldRow, " ");
            textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
            textGraphics.putString(position.getColumn() + newCol, position.getRow() + newRow, String.valueOf(map[newRow][newCol]), SGR.BOLD);
        }

        screen.refresh();
    }

    public void initializeGhost(int row, int col, TextColor.ANSI color, char[][] map) throws IOException {
        textGraphics.setForegroundColor(color);
        textGraphics.putString(position.getColumn() + col, position.getRow() + row, String.valueOf(map[row][col]), SGR.BOLD);
        screen.refresh();
    }

    public void gameOver() throws IOException {
        screen.clear();
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.putString(34,8, "GAME OVER", SGR.BOLD);
        textGraphics.putString(23,9, "Click enter to return to the menu", SGR.BLINK);
        screen.refresh();
    }

    public void writePoints(String points) throws IOException {
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW);
        textGraphics.putString(position.getColumn() - 10, position.getRow(), points, SGR.BOLD);
        screen.refresh();
    }

    public void clearPoints() throws IOException {
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(position.getColumn() - 10, position.getRow(), "         ", SGR.BOLD);
        screen.refresh();
    }

    public void roundWon() throws IOException {
        screen.clear();
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        textGraphics.putString(34,8, "ROUND WON", SGR.BOLD);
        textGraphics.putString(24,9, "Click enter to start next round", SGR.BLINK);
        screen.refresh();
    }
}
