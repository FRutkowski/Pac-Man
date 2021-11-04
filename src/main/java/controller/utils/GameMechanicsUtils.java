package controller.utils;

public class GameMechanicsUtils {
    public static boolean canGoTo(int row, int col, char[][] map) {
        if (map[row][col] != '▉' && map[row][col] != '▅' && map[row][col] != '━') {
            return true;
        } else return false;
    }
}
