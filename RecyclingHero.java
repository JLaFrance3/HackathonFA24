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
        setLayout(new BorderLayout());

        // Create bins
        JPanel binsPanel = new JPanel();
        binsPanel.setLayout(new GridLayout(1, 3));
        binsPanel.add(new Bin(BinType.GLASS));
        binsPanel.add(new Bin(BinType.PLASTIC));
        binsPanel.add(new Bin(BinType.METAL));
        binsPanel.add(new Bin(BinType.PAPER));
        binsPanel.add(new Bin(BinType.TRASH));

        // Create draggable items
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(null);
        itemsPanel.add(createDraggableItem("Paper"));
        itemsPanel.add(createDraggableItem("Plastic"));
        itemsPanel.add(createDraggableItem("Glass"));

        add(binsPanel, BorderLayout.NORTH);
        add(itemsPanel, BorderLayout.CENTER);
    }

    private JLabel createBin(String type) {
        JLabel bin = new JLabel(type + " Bin", SwingConstants.CENTER);
        bin.setTransferHandler(new TransferHandler("text"));
        bin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bin.setPreferredSize(new Dimension(200, 200));
        return bin;
    }

    private JLabel createDraggableItem(String type) {
        JLabel item = new JLabel(type, SwingConstants.CENTER);
        item.setTransferHandler(new TransferHandler("text"));
        item.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        item.setPreferredSize(new Dimension(100, 100));
        item.setOpaque(true);
        item.setBackground(Color.LIGHT_GRAY);

        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JComponent comp = (JComponent) evt.getSource();
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, evt, TransferHandler.COPY);
            }
        });

        return item;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecyclingHero game = new RecyclingHero();
            game.setVisible(true);
        });
    }
}