package Snake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ScoresProperties {
    private static final Properties scoresProps = new Properties();

    public ScoresProperties() {
        loadProperties("scores.properties", scoresProps);
    }

    public int addNewPlayer(String playerName) {
        int id = getPlayersCount();
        setPlayersCount(id + 1);
        String keyPlayer = "Player" + Integer.toString(id);
        scoresProps.setProperty(keyPlayer, playerName);
        setPlayerScores(id, 0);

        return id;
    }

    public void setPlayerScores(int id, int score) {
        String keyScores = "PlayerScores" + Integer.toString(id);
        scoresProps.setProperty(keyScores, Integer.toString(score));
    }

    public int getPlayerScores(int id) {
        String keyScores = "PlayerScores" + Integer.toString(id);
        return Integer.parseInt(scoresProps.getProperty(keyScores));
    }

    public void setPlayersCount(int count) {
        scoresProps.setProperty("playersCount", Integer.toString(count));
    }

    public int getPlayersCount() {
        return Integer.parseInt(scoresProps.getProperty("playersCount"));
    }

    public String getCurrentPlayerName() {
        return scoresProps.getProperty("currentPlayer");
    }

    public String getPlayerName(int id) {
        String key = "Player" + Integer.toString(id);
        return scoresProps.getProperty(key);
    }

    public int getPlayerId(String playerName) {
        for (int i = 0; i < getPlayersCount(); i++) {
            if (getPlayerName(i).equals(playerName)) {
                return i;
            }
        }
        return -1; // Player not found
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
}
