package controller.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static Path choosePath(char[][] map, int rowPositionGhost, int colPositionGhost) {
        System.out.println("dobra pora wybrac droge");
        int x = colPositionGhost;
        int y = rowPositionGhost;

        List<Path> availablePaths = new ArrayList<>();
        if (map[y - 1][x] == '•' || map[y - 1][x] == '⚫' || map[y - 1][x] == ' ') {
            availablePaths.add(Path.TOP);
            System.out.println("moge isc w gore");
        }

        if (map[y + 1][x] == '•' || map[y + 1][x] == '⚫' || map[y + 1][x] == ' ') {
            availablePaths.add(Path.BOTTOM);
            System.out.println("moge isc w dol");
        }

        System.out.println(map[y][x - 2]);
        if (map[y][x - 2] == '•' || map[y][x - 2] == '⚫' || map[y][x - 2] == ' ') {
            availablePaths.add(Path.LEFT);
            System.out.println("moge isc w lewo");
        }

        System.out.println(map[y][x + 2]);
        if (map[y][x + 2] == '•' || map[y][x + 2] == '⚫' || map[y][x + 2] == ' ') {
            availablePaths.add(Path.RIGHT);
            System.out.println("moge isc w prawo");
        }

        if (availablePaths.size() > 2) {
            Random r = new Random();
            return availablePaths.get(r.nextInt(((availablePaths.size() - 1)) + 1));
        }

        if (availablePaths.size() != 0) return availablePaths.get(0);
        else return Path.NOWHERE;
    }
}
