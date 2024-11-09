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
        levels.add(new Level(1, 5, 20, new BinType[]{BinType.GLASS, BinType.TRASH}));
        levels.add(new Level(2, 10, 30, new BinType[]{BinType.GLASS, BinType.TRASH, BinType.PLASTIC}));
        levels.add(new Level(3, 15, 40, new BinType[]{BinType.GLASS, BinType.TRASH, BinType.PLASTIC, BinType.METAL}));
        levels.add(new Level(3, 15, 50, new BinType[]{BinType.GLASS, BinType.TRASH, BinType.PLASTIC, BinType.METAL,
            BinType.PAPER}));
        levels.add(new Level(3, 20, 50, new BinType[]{BinType.GLASS, BinType.TRASH, BinType.PLASTIC, BinType.METAL,
            BinType.PAPER}));
        levels.add(new Level(3, 25, 50, new BinType[]{BinType.GLASS, BinType.TRASH, BinType.PLASTIC, BinType.METAL,
            BinType.PAPER}));
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
