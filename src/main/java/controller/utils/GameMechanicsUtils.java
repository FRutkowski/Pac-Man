package controller.utils;

import java.util.*;

public class GameMechanicsUtils {

    public static boolean canGoTo(int row, int col, char[][] map) {
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
        System.out.println("dobra pora wybrac droge");
        int x = colPositionGhost;
        int y = rowPositionGhost;
        Set<Path> orientantions = new HashSet<>();

        List<Path> availablePaths = new ArrayList<>();
        if (map[y - 1][x] == '•' || map[y - 1][x] == '⚫' || map[y - 1][x] == ' ' || map[y - 1][x] == '━') {
            availablePaths.add(Path.TOP);
            orientantions.add(Path.VERTICAL);
            System.out.println("moge isc w gore");
        }

        if (map[y + 1][x] == '•' || map[y + 1][x] == '⚫' || map[y + 1][x] == ' ') {
            availablePaths.add(Path.BOTTOM);
            orientantions.add(Path.VERTICAL);
            System.out.println("moge isc w dol");
        }

        System.out.println(map[y][x - 2]);
        if (map[y][x - 2] == '•' || map[y][x - 2] == '⚫' || map[y][x - 2] == ' ') {
            availablePaths.add(Path.LEFT);
            orientantions.add(Path.HORIZONTAL);
            System.out.println("moge isc w lewo");
        }

        System.out.println(map[y][x + 2]);
        if (map[y][x + 2] == '•' || map[y][x + 2] == '⚫' || map[y][x + 2] == ' ') {
            availablePaths.add(Path.RIGHT);
            orientantions.add(Path.HORIZONTAL);
            System.out.println("moge isc w prawo");
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
}
