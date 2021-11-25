package controller.utils;

import model.DataBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class GameMechanicsUtils {

    public static boolean canGoTo(int row, int col, char[][] map) {
        if (col > 50) return true;
        if (col < 0) return true;
        if (map[row][col] != '▉' && map[row][col] != '▅' && map[row][col] != '━') {
            return true;
        } else {
            return false;
        }
    }

    public static int calculatePoints(int row, int col, char[][] map) {
        switch (map[row][col]) {
            case '•':
                return 10;
            case '⚫':
                return 100;
            case ' ':
                return 0;
        }

        return 0;
    }

    public static Path choosePath(char[][] map, int rowPositionGhost, int colPositionGhost, Path latestPath) {
        int x = colPositionGhost;
        int y = rowPositionGhost;
        Set<Path> orientantions = new HashSet<>();

        if (x - 2 < 0 && latestPath == Path.LEFT) return Path.LEFT;
        else if (x - 2 < 0 && latestPath == Path.RIGHT) return Path.RIGHT;
        if (x + 2 > 50 && latestPath == Path.RIGHT) return Path.RIGHT;
        else if (x + 2 > 50 && latestPath == Path.LEFT) return Path.LEFT;
        List<Path> availablePaths = new ArrayList<>();
        if (map[y - 1][x] == '•' || map[y - 1][x] == '⚫' || map[y - 1][x] == ' ' || map[y - 1][x] == '━') {
            availablePaths.add(Path.TOP);
            orientantions.add(Path.VERTICAL);
        }

        if (map[y + 1][x] == '•' || map[y + 1][x] == '⚫' || map[y + 1][x] == ' ') {
            availablePaths.add(Path.BOTTOM);
            orientantions.add(Path.VERTICAL);
        }

        if (map[y][x - 2] == '•' || map[y][x - 2] == '⚫' || map[y][x - 2] == ' ') {
            availablePaths.add(Path.LEFT);
            orientantions.add(Path.HORIZONTAL);
        }

        if (map[y][x + 2] == '•' || map[y][x + 2] == '⚫' || map[y][x + 2] == ' ') {
            availablePaths.add(Path.RIGHT);
            orientantions.add(Path.HORIZONTAL);
        }

        if (availablePaths.size() > 2 || (orientantions.contains(Path.HORIZONTAL) && orientantions.contains(Path.VERTICAL))) {
            Random r = new Random();
            return availablePaths.get(r.nextInt(((availablePaths.size() - 1)) + 1));
        }

        if (latestPath == Path.NOWHERE || !canGhostGo(latestPath, map, y, x)) {
            return availablePaths.get(0);
        }

        return latestPath;
    }

    public static boolean canGhostGo(Path path, char[][] map, int row, int col) {
        switch (path) {
            case TOP:
                return canGoTo(row - 1, col, map);
            case BOTTOM:
                return canGoTo(row + 1, col, map);
            case LEFT:
                return canGoTo(row, col - 2, map);
            case RIGHT:
                return canGoTo(row, col + 2, map);
        }

        return false;
    }

    public static void writeScoreToFile(DataBase data) throws IOException {
        File topPlayers = new File("topPlayers.txt");
        topPlayers.createNewFile();


        List<String> linesFromFile = Files.readAllLines(Paths.get("topPlayers.txt"));
        PrintWriter writer = new PrintWriter(topPlayers);
        List<String> linesToWrite = new ArrayList<>();
        boolean foundLineForScore = false;
        int numberOfLine = 0;

        if (linesFromFile.size() == 0) {
            System.out.println("jest 0 linii xD");
            linesToWrite.add(++numberOfLine + ". " + data.getPlayerName() + " " + data.getCurrentPoints());
        }

        for (String currentLine : linesFromFile) {
            ++numberOfLine;
            String[] lineElements = currentLine.split(" ");
            if (!foundLineForScore) {
                if (data.getCurrentPoints() > Integer.parseInt(lineElements[2])) {
                    foundLineForScore = true;
                    linesToWrite.add(numberOfLine + ". " + data.getPlayerName() + " " + data.getCurrentPoints());
                    linesToWrite.add(++numberOfLine + ". " + lineElements[1] + " " + lineElements[2]);
                    continue;
                }

                linesToWrite.add(currentLine);
            } else {
                linesToWrite.add(numberOfLine + ". " + lineElements[1] + " " + lineElements[2]);
            }
        }

        if (!foundLineForScore && linesFromFile.size() != 0) {
            linesToWrite.add(++numberOfLine + ". " + data.getPlayerName() + " " + data.getCurrentPoints());
        }

        writer.print("");
        writer.close();
        PrintWriter writer2 = new PrintWriter(topPlayers);

        for (String line : linesToWrite) {
            writer2.println(line);
        }

        writer2.close();
    }
}
