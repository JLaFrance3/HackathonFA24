import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RecyclingHero extends JFrame {
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
            trashImage = ImageIO.read(new File("Resources/trash bin.png"));
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
        ImagePanel binsPanel = new ImagePanel(grassImage);
        binsPanel.setLayout(null);
        binsPanel.setBounds(0, 50, this.getWidth(), this.getHeight() - scoreBoard.getHeight());

        // Add bins to binsPanel at random positions
        Random random = new Random();
        for (BinType type : level.getItemTypes()) {
            int randomX = random.nextInt(binsPanel.getWidth() - 100); // Adjust 100 for bin width
            int randomY = random.nextInt(binsPanel.getHeight() - 100) + 50; // Adjust for height, add 50 for scoreboard offset

            JLabel bin = createBin(type, new Point(randomX, randomY));
            binsPanel.add(bin);
        }

        // Add draggable items to binsPanel based on level data (position remains fixed)
        for (int i = 0; i < level.getItemCount(); i++) {
            BinType type = level.getItemTypes()[i % level.getItemTypes().length];
            JLabel item = createThrowableItem(type, new Point(100 * (i % 5), 400)); // Fixed position for items
            binsPanel.add(item);
        }

        add(binsPanel);
        revalidate();
        repaint();
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

    private JLabel createThrowableItem(BinType type, Point location) {
        ThrowableItem item = new ThrowableItem(type, location);
        return item;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecyclingHero game = new RecyclingHero();
            game.setVisible(true);
        });
    }
}
