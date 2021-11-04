package controller.utils;

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
}
