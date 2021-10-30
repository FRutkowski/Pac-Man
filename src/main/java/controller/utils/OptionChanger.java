package controller.utils;

public class OptionChanger {
    public static String newGameLevel(boolean isArrowRight, String gameLevel) {
        if (isArrowRight) {
            switch (gameLevel) {
                case "EASY":
                    return "MEDIUM";
                case "MEDIUM":
                    return "HARD";
                case "HARD":
                    return "EASY";
            }
        } else {
            switch (gameLevel) {
                case "EASY":
                    return "HARD";
                case "MEDIUM":
                    return "EASY";
                case "HARD":
                    return "MEDIUM";
            }
        }

        return gameLevel;
    }

    public static String newMapSize(boolean isArrowRight, String mapSize) {
        if (isArrowRight) {
            switch (mapSize) {
                case "SMALL":
                    return "MEDIUM";
                case "MEDIUM":
                    return "BIG";
                case "BIG":
                    return "SMALL";
            }
        } else {
            switch (mapSize) {
                case "SMALL":
                    return "BIG";
                case "MEDIUM":
                    return "SMALL";
                case "BIG":
                    return "MEDIUM";
            }
        }

        return mapSize;
    }
}
