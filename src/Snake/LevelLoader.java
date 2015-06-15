package Snake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class LevelLoader {
    public static int levelsCount = 0;

    private static final Properties levelsProps = new Properties();

    private static String getCellKeyStr(int x, int y, String key) {
        StringBuffer buffer = new StringBuffer(SnakeProperties.cellKey);
        buffer.append(x);
        buffer.append('.');
        buffer.append(y);
        buffer.append('.');
        buffer.append(key);

        return buffer.toString();
    }

    public void generate() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(this.getCellKeyStr(i, j, "tag") + "=0");
            }
        }
    }

    private static void loadProperties(String fileName, Properties properties) {
        try {
            File propsFile = new File(fileName);
            FileInputStream inputStream = new FileInputStream(propsFile);

            properties.load(inputStream);
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public LevelLoader() {
        loadProperties(SnakeProperties.levelsPropsFilename, levelsProps);
        levelsCount = Integer.parseInt(levelsProps.getProperty(SnakeProperties.levelsCountKey));
    }

    public int getLevelsCount() {
        return levelsCount;
    }


    public LevelDescription LoadLevel(int level) {

        Properties levelProps = new Properties();
        StringBuffer fileName = new StringBuffer();
        fileName.append(SnakeProperties.levelsStr);
        fileName.append(File.separatorChar);
        fileName.append(level);
        fileName.append(File.separatorChar);
        fileName.append(SnakeProperties.levelPropsFilename);

        loadProperties(fileName.toString(), levelProps);


        String levelName = levelProps.getProperty("levelName");

        int fieldSizeX = Integer.parseInt(levelProps.getProperty("fieldSizeX"));
        int fieldSizeY = Integer.parseInt(levelProps.getProperty("fieldSizeY"));

        int screenSizeX = Integer.parseInt(levelProps.getProperty("screenSizeX"));
        int screenSizeY = Integer.parseInt(levelProps.getProperty("screenSizeY"));

        int levelProp = Integer.parseInt(levelProps.getProperty("level"));

        if (levelProp != level){
            System.out.println("Level description is incorrect! Resolve level ID");
        }

        LevelDescription levelDescription = new LevelDescription(levelName, fieldSizeX, fieldSizeY,
                screenSizeX, screenSizeY, level);
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                levelDescription.setCellTag(TagType.values()
                        [Integer.parseInt(levelProps.getProperty(this.getCellKeyStr(i, j, "tag")))], i, j);
            }
        }

        levelDescription.setEatTimes(Integer.parseInt(levelProps.getProperty("eatTimes")));
        levelDescription.setTime(Integer.parseInt(levelProps.getProperty("time")));
        levelDescription.setSnakeSpeed(Integer.parseInt(levelProps.getProperty("snakeSpeed")));
        levelDescription.setSnakeX(Integer.parseInt(levelProps.getProperty("snakeX")));
        levelDescription.setSnakeY(Integer.parseInt(levelProps.getProperty("snakeY")));
        levelDescription.setBetweenCellLen(Integer.parseInt(levelProps.getProperty("betweenCellLen")));
        levelDescription.setSnakeCellRadius(Integer.parseInt(levelProps.getProperty("cellRadius")));
        levelDescription.setBackgroundPath(levelProps.getProperty("backgroundPath"));

        return levelDescription;
    }
}
