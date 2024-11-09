import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
* Class to represent a recycling or trash bin
*/
public class Bin extends JLabel {

    private BinType type;
    private Point location;
    private BufferedImage binImage;

    public Bin(BinType type) {
        super(type.name() + " Bin");
        this.type = type;
        this.location = new Point(0, 0);
        setTransferHandler(new TransferHandler("text"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(new Dimension(100, 100));
        setOpaque(true);
        setBackground(Color.CYAN);
    }

    public Bin(BinType type, Point location) {
        this(type);
        setLocation(location);
    }

    public Bin(BinType type, Point location, BufferedImage binImage) {
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
