import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.Random;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

import static org.lwjgl.opengl.GL11.*;

public class SurvivalGame {

    // Window settings
    private long window;
    private final int WIDTH = 800, HEIGHT = 600;

    // Game world settings
    private static final int MAP_SIZE = 5120;
    private static final int TILE_SIZE = 32; // Size of each tile in pixels
    private static final int VIEWPORT_SIZE = 10; // Viewport is 10x10 grid cells

    private char[][] map;
    private int playerX, playerY;

    public void run() {
        init();
        loop();
        cleanup();
    }

    // Initialize GLFW, OpenGL, and create window
    private void init() {
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Set window hints
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);

        // Create window
        window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Survival Game", 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Set up the OpenGL context
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1); // Enable v-sync
        GLFW.glfwShowWindow(window);

        // Initialize OpenGL
        GL.createCapabilities();

        // Initialize the map and player
        initializeMap();
    }

    // Main game loop
    private void loop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            processInput();
            update();
            render();
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    // Handle user input
    private void processInput() {
        if (GLFW.glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) movePlayer(-1, 0); // Move up
        if (GLFW.glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) movePlayer(1, 0);  // Move down
        if (GLFW.glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) movePlayer(0, -1); // Move left
        if (GLFW.glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) movePlayer(0, 1);  // Move right
    }

    // Update the game state
    private void update() {
        // Game logic can go here (e.g., interactions, enemy movement)
    }

    // Render the scene
    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Set the camera view (zoomed-in around the player)
        glLoadIdentity();
        glTranslatef(-playerX * TILE_SIZE + WIDTH / 2, -playerY * TILE_SIZE + HEIGHT / 2, 0); // Center player

        // Draw the map (Viewport centered around the player)
        int startX = Math.max(0, playerX - VIEWPORT_SIZE / 2);
        int startY = Math.max(0, playerY - VIEWPORT_SIZE / 2);
        int endX = Math.min(MAP_SIZE, startX + VIEWPORT_SIZE);
        int endY = Math.min(MAP_SIZE, startY + VIEWPORT_SIZE);

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                float x = (i - startX) * TILE_SIZE;
                float y = (j - startY) * TILE_SIZE;

                switch (map[i][j]) {
                    case '0':
                        glColor3f(0.8f, 0.8f, 0.8f); // Empty space (light gray)
                        break;
                    case 'M':
                        glColor3f(0.0f, 1.0f, 0.0f); // Material (green)
                        break;
                    case 'E':
                        glColor3f(1.0f, 0.0f, 0.0f); // Enemy (red)
                        break;
                }

                glBegin(GL_QUADS);
                glVertex2f(x, y);
                glVertex2f(x + TILE_SIZE, y);
                glVertex2f(x + TILE_SIZE, y + TILE_SIZE);
                glVertex2f(x, y + TILE_SIZE);
                glEnd();
            }
        }

        // Draw player (blue square)
        glColor3f(0.0f, 0.0f, 1.0f); // Player (blue)
        glBegin(GL_QUADS);
        glVertex2f((VIEWPORT_SIZE / 2) * TILE_SIZE, (VIEWPORT_SIZE / 2) * TILE_SIZE);
        glVertex2f((VIEWPORT_SIZE / 2 + 1) * TILE_SIZE, (VIEWPORT_SIZE / 2) * TILE_SIZE);
        glVertex2f((VIEWPORT_SIZE / 2 + 1) * TILE_SIZE, (VIEWPORT_SIZE / 2 + 1) * TILE_SIZE);
        glVertex2f((VIEWPORT_SIZE / 2) * TILE_SIZE, (VIEWPORT_SIZE / 2 + 1) * TILE_SIZE);
        glEnd();
    }

    // Move the player and handle interactions
    private void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        if (newX >= 0 && newX < MAP_SIZE && newY >= 0 && newY < MAP_SIZE) {
            playerX = newX;
            playerY = newY;

            char location = map[playerX][playerY];
            switch (location) {
                case '0':
                    // Empty space, do nothing
                    break;
                case 'M':
                    System.out.println("You found a material!");
                    map[playerX][playerY] = '0'; // Collect material
                    break;
                case 'E':
                    System.out.println("You encountered an enemy!");
                    // Handle battle logic here
                    break;
            }
        }
    }

    // Initialize the map with random clusters of materials and enemies
    private void initializeMap() {
        map = new char[MAP_SIZE][MAP_SIZE];
        Random random = new Random();

        // Fill map with empty spaces
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = '0'; // Empty space
            }
        }

        // Spawn clusters of materials and enemies
        for (int i = 0; i < 1000; i++) { // Adjust cluster count as needed
            int startX = random.nextInt(MAP_SIZE);
            int startY = random.nextInt(MAP_SIZE);
            char type = random.nextBoolean() ? 'E' : 'M'; // 'E' for enemy, 'M' for material

            // Spawn small clusters (3x3 regions)
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int x = startX + dx;
                    int y = startY + dy;

                    if (x >= 0 && x < MAP_SIZE && y >= 0 && y < MAP_SIZE) {
                        map[x][y] = type;
                    }
                }
            }
        }

        // Place player at the center of the map
        playerX = MAP_SIZE / 2;
        playerY = MAP_SIZE / 2;
    }

    // Cleanup resources before exiting
    private void cleanup() {
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public static void main(String[] args) {
        new SurvivalGame().run();
    }
}
