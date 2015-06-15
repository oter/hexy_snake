package Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SnakeScene extends JPanel {

    int time;

    private Timer gameTimer;

    protected void pauseGameTimer() {
        gameTimer.stop();
    }

    protected void continueGameTimer() {
        gameTimer.start();
    }

    private BufferedImage backgroundImage;

    private HexCeil ceilsMatrix[][];

    private LevelDescription levelDescription;

    protected LevelDescription getLevelDescription() {
        return levelDescription;
    }

    public int getScreenWidth() {
        return levelDescription.getScreenWidth();
    }

    public int getScreenHeight() {
        return levelDescription.getScreenHeight();
    }

    public void focus() {
        this.requestFocusInWindow();
    }

    public GameStateProvider getGameStateProvider() {
        return gameStateProvider;
    }

    private GameStateProvider gameStateProvider;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getEatTimes() {
        return eatTimes;
    }

    int eatTimes;

    public int getAteTimes() {
        return ateTimes;
    }

    public void setAteTimes(int ateTimes) {
        this.ateTimes = ateTimes;
    }

    int ateTimes;

    public SnakeScene(GameStateProvider gameStateProvider, int level) {
        this.gameStateProvider = gameStateProvider;

        setFocusable(true);
        requestFocusInWindow();
        loadLevel(level);
    }

    private boolean gameTimerStopped = false;

    protected void stopTimer() {
        gameTimer.setRepeats(false);
        gameTimer.stop();
        gameTimerStopped = true;
    }

    public void endGame() {
        stopTimer();
        getGameStateProvider().setGameState(GameStates.GAME_OVER_MENU);
    }

    private void setLevel(LevelDescription levelDescription) {
        this.levelDescription = levelDescription;
        createCeils();
        time = levelDescription.getTime();
        eatTimes = levelDescription.getEatTimes();
        ateTimes = 0;
        try {
            backgroundImage = ImageIO.read(new File(levelDescription.getBackgroundPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameTimer = new Timer(1, new ActionListener() {
            int timerTicks = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameTimerStopped) {
                    timerTicks++;
                    if (timerTicks > 500) {
                        timerTicks = 0;
                        int time = getTime();
                        if (time == 0) {
                            endGame();
                        }
                        setTime(getTime() - 1);

                        repaint();
                    }
                }
            }
        });
        gameTimer.setRepeats(true);
        gameTimer.start();
    }

    void loadLevel(int level){
        LevelLoader levelLoader = new LevelLoader();
        LevelDescription description = levelLoader.LoadLevel(level);
        setLevel(description);
    }

    void createCeils() {
        ceilsMatrix = new HexCeil[levelDescription.getFieldSizeX()][levelDescription.getFieldSizeY()];
        int ceilsRadius = levelDescription.getSnakeCellRadius();
        double polygonVal = SnakeProperties.getPolygonConstVal();
        int betweenLen = SnakeProperties.betweenLen;

        double ceilsWidth = ceilsRadius * polygonVal + betweenLen;
        double ceilHalf = ceilsRadius * polygonVal;

        for (int i = 0; i < levelDescription.getFieldSizeX(); i++) {
            for (int j = 0; j < levelDescription.getFieldSizeY(); j++) {
                int x = (int)Math.round(ceilsWidth * i * 2 + ceilsWidth * (j % 2 ));
                int y = (int)Math.round((j * 2 * (ceilHalf * polygonVal - betweenLen / 2 * polygonVal +
                        1 / polygonVal * betweenLen)));

                ceilsMatrix[i][j] = new HexCeil(levelDescription.getSnakeCellRadius(), x + SnakeProperties.gameFieldPos.width, y + SnakeProperties.gameFieldPos.height);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(backgroundImage, 0, 0, null);

        // Player name label
        g.setFont(SnakeProperties.playerFont);
        g.setColor(SnakeProperties.playerButtonColor);
        g.drawString(ScoresProperties.getCurrentPlayerName(), 20, 40);

        // Score label
        g.setFont(SnakeProperties.scoresPlayerLabelFont);
        g.setColor(SnakeProperties.scoresLabelColor);
        g.drawString(Integer.toString(getGameStateProvider().getScore()), 25, 80);

        // Current level name label
        g.setFont(SnakeProperties.levelLabelFont);
        g.setColor(SnakeProperties.levelLabelColor);
        g.drawString(levelDescription.getLevelName(), 250, 40);

        // Timer label
        g.setFont(SnakeProperties.scoresPlayerLabelFont);
        g.setColor(SnakeProperties.timerLabelColor);
        g.drawString(Integer.toString(time), 250, 80);

        // etae time label
        g.setFont(SnakeProperties.scoresPlayerLabelFont);
        g.setColor(SnakeProperties.ateTimeLabelColor);
        g.drawString(Integer.toString(ateTimes), 350, 80);

        // draw game field
        for (int i = 0; i < levelDescription.getFieldSizeX(); i++) {
            for (int j = 0; j < levelDescription.getFieldSizeY(); j++) {
                ceilsMatrix[i][j].drawCeil(g, SnakeProperties.getColorByTag(levelDescription.getCellTag(i, j)));
            }
        }
    }
}
