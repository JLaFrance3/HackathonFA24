import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

/*
* Class to represent throwable trash or recycling
*/
public class ThrowableItem extends JLabel{
    private BinType type;
    private Point location;
    private BufferedImage binImage;

    public ThrowableItem(BinType type) {
        super(type.name());
        this.type = type;
        this.location = new Point(0, 0);
        setTransferHandler(new TransferHandler("text"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(new Dimension(100, 100));
        setOpaque(true);
        setBackground(Color.GRAY);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JComponent comp = (JComponent) evt.getSource();
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, evt, TransferHandler.COPY);
            }
        });
    }

    public ThrowableItem(BinType type, Point location) {
        this(type);
        setLocation(location);
    }

    public ThrowableItem(BinType type, Point location, BufferedImage binImage) {
        this(type, location);
        this.binImage = binImage;
    }

    public Point getLocation() {return location;}
    public void setLocation(Point location) {this.location = location;}
    public int getX() {return location.x;}
    public int getY() {return location.y;}
    public BinType getType() {return type;}
    public void setType(BinType type) {this.type = type;}
}
