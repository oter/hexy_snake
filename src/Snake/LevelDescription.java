package Snake;

public class LevelDescription {

    private String backgroundPath;

    private int snakeCellRadius = -1;

    private int betweenCellLen = -1;

    private int fieldSizeX = -1;

    private int fieldSizeY = -1;

    private int level = -1;

    private int screenSizeX = -1;

    private int screenSizeY = -1;

    private int snakeY = -1;

    private int snakeX = -1;

    private int snakeSpeed = -1;

    public int getSnakeCellRadius() {
        return snakeCellRadius;
    }

    public void setSnakeCellRadius(int snakeCellRadius) {
        this.snakeCellRadius = snakeCellRadius;
    }

    public int getBetweenCellLen() {
        return betweenCellLen;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    public void setBetweenCellLen(int betweenCellLen) {
        this.betweenCellLen = betweenCellLen;
    }

    public int getSnakeX() {
        return snakeX;
    }

    public void setSnakeX(int snakeX) {
        this.snakeX = snakeX;
    }

    public int getSnakeY() {
        return snakeY;
    }

    public void setSnakeY(int snakeY) {
        this.snakeY = snakeY;
    }

    public int getSnakeSpeed() {
        return snakeSpeed;
    }

    public void setSnakeSpeed(int snakeSpeed) {
        this.snakeSpeed = snakeSpeed;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    private int time;

    public int getEatTimes() {
        return eatTimes;
    }

    public void setEatTimes(int eatTimes) {
        this.eatTimes = eatTimes;
    }

    private int eatTimes;


    private String levelName;

    private TagType[][] field;

    public LevelDescription(String levelName, int fieldSizeX, int fieldSizeY, int screenSizeX, int screenSizeY, int level) {
        setFieldSize(fieldSizeX, fieldSizeY);
        setLevelName(levelName);
        setScreenSize(screenSizeX, screenSizeY);
        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
        this.level = level;
    }

    public int getFieldSizeX() {
        return fieldSizeX;
    }

    public int getFieldSizeY() {
        return fieldSizeY;
    }

    private void setScreenSize(int screenSizeX, int screenSizeY){
        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
    }

    private void setFieldSize(int fieldSizeX, int fieldSizeY) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        field = new TagType[getFieldSizeX()][getFieldSizeY()];
        for (int i = 0; i < getFieldSizeX(); i++) {
            field[i] = new TagType[getFieldSizeY()];
        }
    }

    public void setCellTag(TagType tag, int x, int y) {
        if (getFieldSizeX() > x && getFieldSizeY() > y)
        {
            field[x][y] = tag;
        }
    }

    public TagType getCellTag(int x, int y) {
        return field[x][y];
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = new String(levelName);
    }

    public int getLevel() {
        return level;
    }

    public int getScreenWidth() {
        return screenSizeX;
    }

    public int getScreenHeight() {
        return screenSizeY;
    }
}
