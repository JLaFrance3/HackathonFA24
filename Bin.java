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
        setBorder(null);
        setSize(new Dimension(200, 200));
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 0));
    }

    public Bin(BinType type, Point location) {
        this(type);
        setLocation(location);
    }

    public Bin(BinType type, Point location, BufferedImage binImage) {
        this(type, location);
        this.binImage = binImage;

        // Set the position and size of the bin
        setBounds(location.x, location.y, 200, 200);  // Adjust size as needed

        if (this.binImage != null) {
            // Set image as the icon if available
            setIcon(new ImageIcon(this.binImage.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        } else {
            // If no image, display a color for differentiation
            setOpaque(true);
            setBackground(Color.LIGHT_GRAY);
            setText(type.toString());
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
        }
    }

    public Point getLocation() {return location;}
    public void setLocation(Point location) {this.location = location;}
    public int getX() {return location.x;}
    public int getY() {return location.y;}
    public BinType getType() {return type;}
    public void setType(BinType type) {this.type = type;}
}
