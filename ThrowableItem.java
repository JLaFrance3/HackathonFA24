import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/*
* Class to represent throwable trash or recycling
*/
public class ThrowableItem extends JLabel{
    private BinType type;
    private Point location;
    private BufferedImage itemImage;
    private Point initialClick;

    public ThrowableItem(BinType type) {
        super(type.name());
        this.type = type;
        setLocation(new Point(0, 0));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(new Dimension(100, 100));
        setOpaque(true);
        setBackground(Color.GRAY);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                System.out.println("click");
                getComponentAt(initialClick);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Get the location of the window
                int thisX = getLocation().x;
                int thisY = getLocation().y;
                System.out.println("drag");
                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move the object to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });
    }

    public ThrowableItem(BinType type, Point location) {
        this(type);
        setLocation(location);
    }

    public ThrowableItem(BinType type, Point location, BufferedImage itemImage) {
        this(type, location);
        this.itemImage = itemImage;
    }

    public Point getLocation() {return location;}
    public void setLocation(Point location) {this.location = location;}
    public int getX() {return location.x;}
    public int getY() {return location.y;}
    public BinType getType() {return type;}
    public void setType(BinType type) {this.type = type;}
}
