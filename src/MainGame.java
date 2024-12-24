import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(10);
        Player player = new Player("Hero", 10, 5, 100);

        Scanner scanner = new Scanner(System.in);
        while (player.isAlive()) {
            gameMap.displayMap();
            System.out.println("Move (W/A/S/D): ");
            char move = scanner.nextLine().toUpperCase().charAt(0);
            gameMap.movePlayer(move);
        }
        scanner.close();
    }
}
