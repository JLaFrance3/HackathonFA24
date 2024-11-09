import javax.swing.*;
import java.awt.*;

/*
* Class to represent a recycling or trash bin
*/
public class Bin extends JLabel {

    private BinType type;
    private Point location;

    public Bin(BinType type) {
        super(type + " Bin", SwingConstants.CENTER);
        this.type = type;
        this.location = new Point(0, 0);
        setTransferHandler(new TransferHandler("text"));
        setPreferredSize(new Dimension(200, 200));
    }

    public Bin(BinType type, Point location) {
        super(type + " Bin", SwingConstants.CENTER);
        this.type = type;
        this.location = location;
        setTransferHandler(new TransferHandler("text"));
        setPreferredSize(new Dimension(200, 200));
    }

    public Point getLocation() {return location;}
    public void setLocation(Point location) {this.location = location;}
    public int getX() {return location.x;}
    public int getY() {return location.y;}
    public BinType getType() {return type;}
    public void setType(BinType type) {this.type = type;}
}
