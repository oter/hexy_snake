package Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SnakeScene extends JPanel {

    private BufferedImage backgroundImage;

    private HexCeil ceilsMatrix[][];

    private LevelDescription levelDescription;

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

    private Snake snake;

    private Timer gameTimer;

    private int currentScore = 0;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    int time = 0;

    int eatTimes = 0;

    int ateTimes = 10;

    public SnakeScene(GameStateProvider gameStateProvider, int level) {
        this.gameStateProvider = gameStateProvider;
        currentScore = getGameStateProvider().getScore();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    getGameStateProvider().setGameState(GameStates.GAME_OVER_MENU);
                    getGameStateProvider().setScore(currentScore);
                }
                if (snake != null) {
                    snake.changeDirection(e);
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
        loadLevel(level);
    }

    public void endGame() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        getGameStateProvider().setGameState(GameStates.GAME_OVER_MENU);
    }

    public void setLevel(final LevelDescription levelDescription) {
        this.levelDescription = levelDescription;
        createCeils();
        time = levelDescription.getTime();
        eatTimes = levelDescription.getEatTimes();
        snake = new Snake(levelDescription, getGameStateProvider(), levelDescription.getSnakeX(), levelDescription.getSnakeY());
        try {
            backgroundImage = ImageIO.read(new File(levelDescription.getBackgroundPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameTimer = new Timer(1, new ActionListener() {
            int timerTicks = 0;
            int snakeTicks = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                timerTicks++;
                snakeTicks++;

                if (snakeTicks > (int)((double) 1/ levelDescription.getSnakeSpeed() * 500)) {
                    snakeTicks = 0;
                    snake.snakeMove();
                    repaint();
                }

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
        });
        gameTimer.setRepeats(true);
        gameTimer.start();
    }

    void loadLevel(int level){
        LevelLoader levelLoader = new LevelLoader();
        LevelDescription description = levelLoader.LoadLevel(level);
        setLevel(description);
        System.out.println("Levels count: " + String.valueOf(levelLoader.getLevelsCount()));
        System.out.println("Level: " + String.valueOf(description.getLevelName()));
    }

    public void redraw(Graphics g) {
        for (int i = 0; i < levelDescription.getFieldSizeX(); i++) {
            for (int j = 0; j < levelDescription.getFieldSizeY(); j++) {
                ceilsMatrix[i][j].drawCeil(g, SnakeProperties.getColorByTag(levelDescription.getCellTag(i, j)));
            }
        }
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

        //g.setColor(SnakeProperties.backgroundColor);
        //g.fillRect(0, 0, getScreenWidth(), getScreenHeight());

        g.drawImage(backgroundImage, 0, 0, null);

        // Player name label
        g.setFont(SnakeProperties.playerFont);
        g.setColor(SnakeProperties.playerButtonColor);
        g.drawString(ScoresProperties.getCurrentPlayerName(), 20, 40);

        // Score label
        g.setFont(SnakeProperties.scoresPlayerLabelFont);
        g.setColor(SnakeProperties.scoresLabelColor);
        g.drawString(Integer.toString(currentScore), 25, 80);

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
        redraw(g);

        //draw snake body
        if (snake != null) {
            snake.redraw(g);
        }
    }
}
