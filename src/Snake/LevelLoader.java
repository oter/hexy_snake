package Snake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class LevelLoader {
    private static final String levelsPropsFilename = "level_props.properties";

    private static final String levelPropsFilename = "level.properties";

    private static final String levelsStr = "levels";

    private static final Properties levelsProps = new Properties();

    private static int levelsCount = 0;

    private static final String levelsCountKey = "levelsCount";

    private static final String cellKey = "Cell.";


    private static String getCellKeyStr(int x, int y, String key) {
        StringBuffer buffer = new StringBuffer(cellKey);
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
        loadProperties(levelsPropsFilename, levelsProps);
        levelsCount = Integer.parseInt(levelsProps.getProperty(levelsCountKey));
    }

    public int getLevelsCount() {
        return levelsCount;
    }


    public LevelDescription LoadLevel(int level) {

        Properties levelProps = new Properties();
        StringBuffer fileName = new StringBuffer();
        fileName.append(levelsStr);
        fileName.append(File.separatorChar);
        fileName.append(level);
        fileName.append(File.separatorChar);
        fileName.append(levelPropsFilename);

        loadProperties(fileName.toString(), levelProps);


        String levelName = levelProps.getProperty("levelName");

        int fieldSizeX = Integer.parseInt(levelProps.getProperty("fieldSizeX"));
        int fieldSizeY = Integer.parseInt(levelProps.getProperty("fieldSizeY"));

        int screenSizeX = Integer.parseInt(levelProps.getProperty("screenSizeX"));
        int screenSizeY = Integer.parseInt(levelProps.getProperty("screenSizeY"));

        LevelDescription levelDescription = new LevelDescription(levelName, fieldSizeX, fieldSizeY, screenSizeX, screenSizeY);
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                levelDescription.setCellTag(TagType.values()[Integer.parseInt(levelProps.getProperty(this.getCellKeyStr(i, j, "tag")))], i, j);
            }
        }

        return levelDescription;
    }

    public void printLevelsInfo(Properties p) {
        Enumeration keys = p.keys();
        while(keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = p.getProperty(key);
            System.out.println("Key: " + key + ": " + value);
        }
    }
}
