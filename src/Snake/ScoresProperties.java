package Snake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Properties;



public class ScoresProperties {

    private static final Properties scoresProps = new Properties();

    public ScoresProperties() {
        loadProperties("scores.properties", scoresProps);
    }

    public static LinkedList<ScoreRecord> getScoresRecords() {
        LinkedList<ScoreRecord> records = new LinkedList<ScoreRecord>();

        for (int i = 0; i < getPlayersCount(); i++) {
            ScoreRecord record = new ScoreRecord(getPlayerName(i), getPlayerScore(i));
            records.add(record);
        }

        Collections.sort(records, new Comparator<ScoreRecord>() {
            @Override
            public int compare(ScoreRecord o1, ScoreRecord o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        return records;
    }

    public static int addNewPlayer(String playerName) {
        int id = getPlayersCount();
        setPlayersCount(id + 1);
        String keyPlayer = "Player" + Integer.toString(id);
        scoresProps.setProperty(keyPlayer, playerName);
        setPlayerScores(id, 0);

        return id;
    }

    public static void setPlayerScores(int id, int score) {
        String keyScores = "PlayerScores" + Integer.toString(id);
        scoresProps.setProperty(keyScores, Integer.toString(score));
    }

    public static int getPlayerScore(int id) {
        String keyScores = "PlayerScores" + Integer.toString(id);
        return Integer.parseInt(scoresProps.getProperty(keyScores));
    }

    public static int getPlayerScore(String playerName) {
        int id = getPlayerId(playerName);
        if (id  != -1) {
            return getPlayerScore(id);
        } else {
            return 0;
        }
    }

    public static void setPlayersCount(int count) {
        scoresProps.setProperty("playersCount", Integer.toString(count));
    }

    public static int getPlayersCount() {
        return Integer.parseInt(scoresProps.getProperty("playersCount"));
    }

    public static String getCurrentPlayerName() {
        return scoresProps.getProperty("currentPlayer");
    }

    public static String getPlayerName(int id) {
        String key = "Player" + Integer.toString(id);
        return scoresProps.getProperty(key);
    }

    public static int getPlayerId(String playerName) {
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
