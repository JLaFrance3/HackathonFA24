import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RecyclingHero extends JFrame {
    private ScoreBoard scoreBoard;
    private BufferedImage glassImage = null;

    private LevelManager levelManager;

    public RecyclingHero() {
        setTitle("Recycling Hero");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        try {
            glassImage = ImageIO.read(new File("Resources/glassBin.png"));
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
        scoreBoard.setBounds(0, 0, 800, 50); // Set position and size of the scoreboard
        add(scoreBoard);

        // Create bins panel
        JPanel binsPanel = new JPanel();
        binsPanel.setLayout(null);
        binsPanel.setBounds(0, 50, 800, 550); // Set bounds for binsPanel

        // Add bins to binsPanel based on level data
        int binX = 0;
        int binY = 0;
        for (BinType type : level.getItemTypes()) {
            JLabel bin = createBin(type, new Point(binX, binY));
            binsPanel.add(bin);

            binX += 150; // Adjust spacing between bins
            if (binX > binsPanel.getWidth()) { // Move to next row if needed
                binX = 0;
                binY += 150;
            }
        }

        // Add draggable items to binsPanel based on level data
        for (int i = 0; i < level.getItemCount(); i++) {
            BinType type = level.getItemTypes()[i % level.getItemTypes().length];
            JLabel item = createThrowableItem(type, new Point(100 * (i % 5), 400));
            binsPanel.add(item);
        }

        add(binsPanel);
        revalidate();
        repaint();
    }

    private void endGame() {
        JOptionPane.showMessageDialog(this, "Game Over! Your score: " + scoreBoard.getScore());
        System.exit(0);
    }

    //Add bin to random panel location
    private JLabel createBin(BinType type, Point location) {
        Bin bin = new Bin(type, location);
        switch (type) {
            case GLASS:
                bin.setBackground(Color.CYAN);
                break;
            case PLASTIC:
                bin.setBackground(Color.YELLOW);
                break;
            case METAL:
                bin.setBackground(Color.GRAY);
                break;
            case PAPER:
                bin.setBackground(Color.WHITE);
                break;
            case TRASH:
                bin.setBackground(Color.BLACK);
                break;
        }
        bin.setOpaque(true); // Ensure the background color is visible
        return bin;
    }

    //Add item to random panel location
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