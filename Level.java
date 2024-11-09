
/*
* Class for individuals levels of game
*/
public class Level {
    private int levelNumber;
    private int itemCount;
    private int timeLimit; // in seconds
    private BinType[] itemTypes;

    public Level(int levelNumber, int itemCount, int timeLimit, BinType[] itemTypes) {
        this.levelNumber = levelNumber;
        this.itemCount = itemCount;
        this.timeLimit = timeLimit;
        this.itemTypes = itemTypes;
    }

    public int getLevelNumber() {return levelNumber;}
    public int getItemCount() {return itemCount;}
    public int getTimeLimit() {return timeLimit;}
    public BinType[] getItemTypes() {return itemTypes;}
}