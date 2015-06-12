package Snake;

public class LevelDescription {
    private int fieldSizeX = -1;

    private int fieldSizeY = -1;

    private int level = -1;

    private int screenSizeX = -1;

    private int screenSizeY = -1;

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

    public int getScreenSizeX() {
        return screenSizeX;
    }

    public int getScreenSizeY() {
        return screenSizeY;
    }
}
