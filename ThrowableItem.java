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
    private Point position;
    private BufferedImage itemImage;
    private Point initialClick;

    public ThrowableItem(BinType type) {
        super(type.name());
        this.type = type;
        this.position = new Point(0, 0);
        setLocation(position);
        setTransferHandler(new TransferHandler("text"));
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
                
                position = new Point(X, Y);
                setLocation(position);
            }
        });
    }

    public ThrowableItem(BinType type, Point position) {
        this(type);
        this.position = position;
        setLocation(position);
    }

    public ThrowableItem(BinType type, Point position, BufferedImage itemImage) {
        this(type, position);
        this.itemImage = itemImage;
    }

    public Point getPosition() {return position;}
    public void setPosition(Point position) {this.position = position;}
    public int getX() {return position.x;}
    public int getY() {return position.y;}
    public BinType getType() {return type;}
    public void setType(BinType type) {this.type = type;}
}
