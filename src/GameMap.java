import java.util.Scanner;

public class GameMap {
    private char[][] map;
    private int playerX, playerY;

    public GameMap(int size) {
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = '0'; // empty
            }
        }
        playerX = size / 2;
        playerY = size / 2;
        map[playerX][playerY] = 'P'; // player start position
    }

    public void movePlayer(char direction) {
        map[playerX][playerY] = '0';
        switch (direction) {
            case 'W': playerX = Math.max(0, playerX - 1); break;
            case 'S': playerX = Math.min(map.length - 1, playerX + 1); break;
            case 'A': playerY = Math.max(0, playerY - 1); break;
            case 'D': playerY = Math.min(map[0].length - 1, playerY + 1); break;
            default: System.out.println("Invalid move"); return;
        }
        map[playerX][playerY] = 'P';
    }

    public void displayMap() {
        for (char[] row : map) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
