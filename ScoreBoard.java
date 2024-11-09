import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {

    private int score = 0;
    private int timeLeft = 60;
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
}
