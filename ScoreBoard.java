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
        scoreLabel = new JLabel("Score: 0");
        timerLabel = new JLabel("Time: " + timeLeft);
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
