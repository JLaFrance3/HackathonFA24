import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        setSize(1600, 1600);
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
        resetGame(); // Call resetGame to restart instead of exiting
    }

    private void resetGame() {
        // Reset score and timer
        scoreBoard.reset(); // This method should reset score and timer in ScoreBoard

        // Optionally, reset other components' states as needed, such as positions of items

        // Restart the timer
        scoreBoard.startTimer(this::endGame);
    }

    //Add bin to random panel location
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