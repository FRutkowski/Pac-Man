package controller.utils;

import model.DataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameActivator {
    public static char[][] initMap(DataBase data) throws FileNotFoundException {
        char[][] map = new char[data.getMapRows()][data.getMapColumns()];
        File file = new File("map-medium.txt");
        Scanner scanner = new Scanner(file);
        int j = 0;
        String currentLine;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            for (int i = 0; i < data.getMapColumns(); i++) {
                map[j][i] = currentLine.charAt(i);
            }

            j++;
        }

        for (int i = 0; i < data.getMapRows(); i++) {
            for (int k = 0; k < data.getMapColumns(); k++) {
                System.out.print(map[i][k]);
            }
        }

        return map;
    }
}
