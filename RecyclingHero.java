import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RecyclingHero extends JFrame {
    private ImagePanel binsPanel;
    
    BufferedImage glassImage = null;
    BufferedImage plasticImage = null;
    BufferedImage metalImage = null;
    BufferedImage paperImage = null;
    BufferedImage trashImage = null;
    BufferedImage grassImage = null;

    private ScoreBoard scoreBoard;    
    private LevelManager levelManager;

    public RecyclingHero() {
        setTitle("Recycling Hero");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        try {
            glassImage = ImageIO.read(new File("Resources/glassBin.png"));
            plasticImage = ImageIO.read(new File("Resources/plasticBin.png"));
            metalImage = ImageIO.read(new File("Resources/metalBin.png"));
            paperImage = ImageIO.read(new File("Resources/goodPaperBin.png"));
            trashImage = ImageIO.read(new File("Resources/trashbin.png"));
            grassImage = ImageIO.read(new File("Resources/coolGrass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Start level manager
        levelManager = new LevelManager();
        loadLevel(levelManager.getCurrentLevel());

        // Start the game timer
        scoreBoard.startTimer(this::endGame);
    }

    private void loadLevel(Level level) {
        // Clear existing components
        getContentPane().removeAll();

        // Initialize and add the ScoreBoard at the top of the window
        scoreBoard = new ScoreBoard();
        scoreBoard.setBounds(0, 0, this.getWidth(), 50);
        add(scoreBoard);

        // Create bins panel with grass background image
        binsPanel = new ImagePanel(grassImage);
        binsPanel.setLayout(null);
        binsPanel.setBounds(0, 50, this.getWidth(), this.getHeight() - scoreBoard.getHeight());

        // Add bins to binsPanel at random positions without overlap
        Random random = new Random();
        for (BinType type : level.getItemTypes()) {
            Point newLocation = generateRandomPosition(binsPanel, 200, 200); // 100x100 size for bins
            JLabel bin = createBin(type, newLocation);
            binsPanel.add(bin);
        }

        // Add draggable items to binsPanel at fixed positions
        for (int i = 0; i < level.getItemCount(); i++) {
            // Define fixed positions for items
            Point newLocation = generateRandomPosition(binsPanel, 200, 200);
            BinType type = level.getItemTypes()[i % level.getItemTypes().length];
            JLabel item = createThrowableItem(type, new Point(newLocation.x, newLocation.y));
            binsPanel.add(item);
        }

        add(binsPanel);
        revalidate();
        repaint();
    }

    // Generate random position while avoiding overlap (for bins only)
    private Point generateRandomPosition(Container container, int width, int height) {
        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1;  // Generates a number between 1 and 1000
        boolean overlap;
        Point point;
        do {
            overlap = false;
            int x = random.nextInt(binsPanel.getWidth() - width);
            int y = random.nextInt(Math.max(1, binsPanel.getHeight() - height - 50)) + 50;
            point = new Point(x, y);

            // Check for overlap with existing components (bins only)
            for (Component c : container.getComponents()) {
                if (c.getBounds().intersects(new Rectangle(point.x, point.y, width, height))) {
                    overlap = true;
                    break;
                }
            }
        } while (overlap);

        return point;
    }

    private void endGame() {
        JOptionPane.showMessageDialog(this, "Game Over! Your score: " + scoreBoard.getScore());
        resetGame();
    }

    private void resetGame() {
        scoreBoard.reset();
        scoreBoard.startTimer(this::endGame);
    }

    private JLabel createBin(BinType type, Point location) {
        BufferedImage binImage;
        switch (type) {
            case GLASS -> binImage = glassImage;
            case METAL -> binImage = metalImage;
            case PAPER -> binImage = paperImage;
            case PLASTIC -> binImage = plasticImage;
            case TRASH -> binImage = trashImage;
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        }

        Bin bin = new Bin(type, location, binImage);
        bin.setOpaque(true);
        return bin;
    }

    private JLabel createThrowableItem(BinType type, Point position) {
        ThrowableItem item = new ThrowableItem(type, position);
        return item;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecyclingHero game = new RecyclingHero();
            game.setVisible(true);
        });
    }
}
