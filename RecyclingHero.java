import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RecyclingHero extends JFrame {
    BufferedImage glassImage = null;
    
    public RecyclingHero() {
        setTitle("Recycling Hero");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        try {
            glassImage = ImageIO.read(new File("Resources\\glassBin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create bins
        JPanel binsPanel = new JPanel();
        binsPanel.setLayout(null); // set icon and set border to null
        binsPanel.setBounds(0, 0, 800, 600);
        binsPanel.add(new Bin(BinType.GLASS, new Point(0, 0), glassImage));
        binsPanel.add(new Bin(BinType.PLASTIC, new Point(100, 10)));
        binsPanel.add(new Bin(BinType.METAL, new Point(400,0)));
        binsPanel.add(new Bin(BinType.PAPER, new Point(50, 300)));
        binsPanel.add(new Bin(BinType.TRASH, new Point(500, 200)));

        // Add draggable items to binsPanel
        binsPanel.add(new ThrowableItem(BinType.GLASS, new Point(0, 400)));
        binsPanel.add(new ThrowableItem(BinType.PLASTIC, new Point(100, 400)));
        binsPanel.add(new ThrowableItem(BinType.METAL, new Point(200, 400)));
        binsPanel.add(new ThrowableItem(BinType.PAPER, new Point(300, 400)));
        binsPanel.add(new ThrowableItem(BinType.TRASH, new Point(400, 400)));

        add(binsPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecyclingHero game = new RecyclingHero();
            game.setVisible(true);
        });
    }
}