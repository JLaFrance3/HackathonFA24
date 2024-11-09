import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class ThrowableItem extends JLabel {
    private BinType type;
    private Point position;
    private BufferedImage itemImage;
    private Point initialClick;
    private Point2D.Double velocity;
    private Timer timer;
    private long lastTime;

    public ThrowableItem(BinType type) {
        super(type.name());
        this.type = type;
        this.position = new Point(0, 0);
        this.velocity = new Point2D.Double(0, 0);
        setLocation(position);
        setTransferHandler(new TransferHandler("text"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(new Dimension(100, 100));
        setOpaque(true);
        setBackground(Color.GRAY);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                lastTime = System.currentTimeMillis();
            }

            public void mouseReleased(MouseEvent e) {
                long currentTime = System.currentTimeMillis();
                long timeElapsed = currentTime - lastTime;

                // Calculate velocity based on drag distance and time
                int xMoved = e.getXOnScreen() - initialClick.x;
                int yMoved = e.getYOnScreen() - initialClick.y;
                if (timeElapsed > 0) {
                    velocity = new Point2D.Double((xMoved * 10.0) / timeElapsed, (yMoved * 10.0) / timeElapsed);
                } else {
                    velocity = new Point2D.Double(xMoved, yMoved);
                }
                startThrowing();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Get the location of the window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

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

    private void startThrowing() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Apply friction
                velocity.x *= 0.99;
                velocity.y *= 0.99;

                // Update position
                position.x += velocity.x;
                position.y += velocity.y;

                // Check for boundaries and bounce
                if (position.x < 0 || position.x > getParent().getWidth() - getWidth()) {
                    velocity.x = -velocity.x;
                    position.x = Math.max(0, Math.min(position.x, getParent().getWidth() - getWidth()));
                }
                if (position.y < 0 || position.y > getParent().getHeight() - getHeight()) {
                    velocity.y = -velocity.y;
                    position.y = Math.max(0, Math.min(position.y, getParent().getHeight() - getHeight()));
                }

                // Stop the timer if the item is moving very slowly
                if (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1) {
                    timer.cancel();
                }

                setLocation(position);
            }
        }, 0, 30); // Update every 30 milliseconds
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

    public Point getPosition() { return position; }
    public void setPosition(Point position) { this.position = position; }
    public int getX() { return position.x; }
    public int getY() { return position.y; }
    public BinType getType() { return type; }
    public void setType(BinType type) { this.type = type; }
}
