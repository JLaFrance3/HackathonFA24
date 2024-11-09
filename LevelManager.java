import java.util.ArrayList;
import java.util.List;

/*
* Enum to represent bin types
*/
public class LevelManager {
    private List<Level> levels;
    private int currentLevelIndex;

    public LevelManager() {
        levels = new ArrayList<>();
        currentLevelIndex = 0;
        loadLevels();
    }

    private void loadLevels() {
        // Example levels
        levels.add(new Level(1, 5, 60, new BinType[]{BinType.GLASS, BinType.PLASTIC}));
        levels.add(new Level(2, 10, 90, new BinType[]{BinType.GLASS, BinType.PLASTIC, BinType.METAL}));
        levels.add(new Level(3, 15, 120, new BinType[]{BinType.GLASS, BinType.PLASTIC, BinType.METAL, BinType.PAPER}));
    }

    public Level getCurrentLevel() {
        return levels.get(currentLevelIndex);
    }

    public void nextLevel() {
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
        } else {
            System.out.println("Congratulations! You've completed all levels.");
        }
    }

    public boolean hasNextLevel() {
        return currentLevelIndex < levels.size() - 1;
    }
}
