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

    public RecyclingHero() {
        setTitle("Recycling Hero");
        setSize(1600, 1600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Initialize and add ScoreBoard panel at the bottom of the window
        scoreBoard = new ScoreBoard();
        scoreBoard.setBounds(0, 550, 800, 50); // Set position and size of ScoreBoard
        add(scoreBoard);

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


        // Create bins panel and position it below the scoreboard
        ImagePanel binsPanel = new ImagePanel(grassImage);
        binsPanel.setLayout(null); // set icon and set border to null
        binsPanel.setBounds(0, 0, 1600, 1600);
        
        binsPanel.add(new Bin(BinType.GLASS, new Point(200, 50), glassImage));
        binsPanel.add(new Bin(BinType.PLASTIC, new Point(100, 50), plasticImage));
        binsPanel.add(new Bin(BinType.METAL, new Point(400,110), metalImage));
        binsPanel.add(new Bin(BinType.PAPER, new Point(50, 300), paperImage));
        binsPanel.add(new Bin(BinType.TRASH, new Point(500, 200), trashImage));


        // Add draggable items to binsPanel
        binsPanel.add(new ThrowableItem(BinType.GLASS, new Point(0, 400)));
        binsPanel.add(new ThrowableItem(BinType.PLASTIC, new Point(100, 400)));
        binsPanel.add(new ThrowableItem(BinType.METAL, new Point(200, 400)));
        binsPanel.add(new ThrowableItem(BinType.PAPER, new Point(300, 400)));
        binsPanel.add(new ThrowableItem(BinType.TRASH, new Point(400, 400)));

        add(binsPanel);

        // Start the game timer
        scoreBoard.startTimer(this::endGame);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecyclingHero game = new RecyclingHero();
            game.setVisible(true);
        });
    }
}
