import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SurvivalGame extends JFrame implements KeyListener {
    private final int mapSize = 10;
    private char[][] map;
    private int playerX;
    private int playerY;

    public SurvivalGame() {
        // Setări fereastră
        setTitle("Survival Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inițializare hartă
        map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = '0'; // locație goală
            }
        }
        playerX = mapSize / 2;
        playerY = mapSize / 2;
        map[playerX][playerY] = 'P'; // poziția jucătorului

        // Adăugare panel pentru desenare
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        // Capturare evenimente tastatură
        addKeyListener(this);

        // Afișare fereastră
        setVisible(true);
    }

    // Gestionare mișcare
    private void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        // Verificare limite hartă
        if (newX >= 0 && newX < mapSize && newY >= 0 && newY < mapSize) {
            map[playerX][playerY] = '0'; // eliberează locația veche
            playerX = newX;
            playerY = newY;
            map[playerX][playerY] = 'P'; // actualizează locația jucătorului
        }
        repaint(); // redesenează harta
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> movePlayer(-1, 0); // sus
            case KeyEvent.VK_S -> movePlayer(1, 0);  // jos
            case KeyEvent.VK_A -> movePlayer(0, -1); // stânga
            case KeyEvent.VK_D -> movePlayer(0, 1);  // dreapta
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    // Clasa pentru desenarea hărții
    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int cellSize = getWidth() / mapSize; // dimensiune celulă

            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {
                    // Setare culoare celulă
                    if (map[i][j] == 'P') {
                        g.setColor(Color.BLUE); // jucător
                    } else if (map[i][j] == '0') {
                        g.setColor(Color.LIGHT_GRAY); // locație goală
                    } else if (map[i][j] == 'E') {
                        g.setColor(Color.RED); // inamic
                    } else if (map[i][j] == 'O') {
                        g.setColor(Color.GREEN); // obiect
                    }

                    // Desenare celulă
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    g.setColor(Color.BLACK); // contur celulă
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }


    // Metoda principală
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SurvivalGame::new);
    }
}
