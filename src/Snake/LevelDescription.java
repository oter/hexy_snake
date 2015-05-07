package Snake;

public class LevelDescription {
    private int fieldSizeX = 0;

    private int fieldSizeY = 0;

    private int screenSizeX = 0;

    private int screenSizeY = 0;

    private String levelName;

    private TagType[][] field;

    public LevelDescription(String levelName, int fieldSizeX, int fieldSizeY, int screenSizeX, int screenSizeY) {
        setFieldSize(fieldSizeX, fieldSizeY);
        setLevelName(levelName);
        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
    }

    public int getFieldSizeX() {
        return fieldSizeX;
    }

    public int getFieldSizeY() {
        return fieldSizeY;
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
}
