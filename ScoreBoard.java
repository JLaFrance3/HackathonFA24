import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {

    private int score = 0;
    private int timeLeft = 5;
    private JLabel scoreLabel;
    private JLabel timerLabel;
    private Timer timer;

    public ScoreBoard() {
        setLayout(new FlowLayout());

        // Create and customize the score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font, style, and size
        scoreLabel.setForeground(Color.BLUE); // Set text color to blue

        // Create and customize the timer label
        timerLabel = new JLabel("Time: " + timeLeft);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font, style, and size
        timerLabel.setForeground(Color.RED); // Set text color to red

        // Add labels to the panel
        add(scoreLabel);
        add(timerLabel);
    }

    public void increaseScore() {
        score += 10;
        scoreLabel.setText("Score: " + score);
    }

    public int getScore() {
        return score;
    }

    public void startTimer(Runnable endGameCallback) {
        // Stop any previous timer if it exists
        if (timer != null) {
            timer.stop();
        }
        
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft);
            if (timeLeft <= 0) {
                timer.stop();
                endGameCallback.run();
            }
        });
        timer.start();
    }

    public void reset() {
        // Reset score and timer
        score = 0;
        timeLeft = 60;
        scoreLabel.setText("Score: 0");
        timerLabel.setText("Time: " + timeLeft);

        // Stop any active timer before starting a new game
        if (timer != null) {
            timer.stop();
        }
    }
}
