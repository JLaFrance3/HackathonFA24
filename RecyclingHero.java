import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecyclingHero extends JFrame {

    public RecyclingHero() {
        setTitle("Recycling Hero");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create bins
        JPanel binsPanel = new JPanel();
        binsPanel.setLayout(null);
        binsPanel.setBounds(0, 0, 800, 600);
        binsPanel.add(new Bin(BinType.GLASS, new Point(0, 0)));
        binsPanel.add(new Bin(BinType.PLASTIC, new Point(100, 10)));
        binsPanel.add(new Bin(BinType.METAL, new Point(400,0)));
        binsPanel.add(new Bin(BinType.PAPER, new Point(50, 300)));
        binsPanel.add(new Bin(BinType.TRASH, new Point(500, 200)));
        // binsPanel.add(createDraggableItem(BinType.GLASS));
        // binsPanel.add(createDraggableItem(BinType.PLASTIC));
        // binsPanel.add(createDraggableItem(BinType.METAL));
        // binsPanel.add(createDraggableItem(BinType.PAPER));
        // binsPanel.add(createDraggableItem(BinType.TRASH));

        add(binsPanel);
    }

    // private JLabel createDraggableItem(BinType type) {
    //     Bin item = new Bin(type);
    //     item.setTransferHandler(new TransferHandler("text"));
    //     item.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    //     item.setPreferredSize(new Dimension(100, 100));
    //     item.setOpaque(true);
    //     item.setBackground(Color.LIGHT_GRAY);

    //     item.addMouseListener(new java.awt.event.MouseAdapter() {
    //         public void mousePressed(java.awt.event.MouseEvent evt) {
    //             JComponent comp = (JComponent) evt.getSource();
    //             TransferHandler handler = comp.getTransferHandler();
    //             handler.exportAsDrag(comp, evt, TransferHandler.COPY);
    //         }
    //     });

    //     return item;
    // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecyclingHero game = new RecyclingHero();
            game.setVisible(true);
        });
    }
}