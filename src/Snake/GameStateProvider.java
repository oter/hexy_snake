package Snake;


import javax.swing.*;
import java.awt.*;

public class GameStateProvider extends JFrame {

    private static final ScoresProperties scoresProperties = new ScoresProperties();

    private static GameStates gameState;

    private int currentLevel;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    //public void setCurrentScore(int currentScore) {
     //   this.currentScore = currentScore;
    //}

    public static GameStates getGameState() {
        return gameState;
    }

    private void snakeLoad(int level) {
        Snake snake = new Snake(this, level);
        setContentPane(snake);
        Dimension screenSize = new Dimension(snake.getScreenWidth(), snake.getScreenHeight());
        setSize(screenSize);
        setMinimumSize(screenSize);
        snake.focus();
    }

    public void setGameState(GameStates gameState) {
        GameStateProvider.gameState = gameState;
        switch (gameState) {
            case MAIN_MENU:
                MainMenu mainMenu = new MainMenu(this);
                setContentPane(mainMenu);
                setSize(mainMenu.getSize());
                setMinimumSize(mainMenu.getSize());
                break;

            case NEW_PLAYER_MENU:
                NewPlayerMenu newPlayerMenu = new NewPlayerMenu(this);
                setContentPane(newPlayerMenu);
                setSize(newPlayerMenu.getSize());
                setMinimumSize(newPlayerMenu.getSize());
                newPlayerMenu.focusUserField();
                break;
            case NEXT_LEVEL:
                int current = getCurrentLevel();
                System.out.println("next level: " + Integer.toString(current));
                if (current == LevelLoader.getLevelsCount()) {
                    setGameState(GameStates.GAME_OVER_MENU);
                } else {
                    setCurrentLevel(getCurrentLevel() + 1);
                    snakeLoad(getCurrentLevel());
                }

                break;
            case PLAY_GAME:
                setCurrentLevel(1);
                setScore(0);
                snakeLoad(getCurrentLevel());

                break;
            case SCORES_MENU:
                ScoresMenu scoresMenu = new ScoresMenu(this);
                setContentPane(scoresMenu);
                setSize(scoresMenu.getSize());
                setMinimumSize(scoresMenu.getSize());
                scoresMenu.focus();
                break;
            case GAME_OVER_MENU:
                GameOverMenu gameOverMenu = new GameOverMenu(this, getScore());
                setContentPane(gameOverMenu);
                setSize(gameOverMenu.getSize());
                setMinimumSize(gameOverMenu.getSize());
                int id = ScoresProperties.getPlayerId(ScoresProperties.getCurrentPlayerName());
                int lastScore = ScoresProperties.getPlayerScore(id);
                int currentScore = getScore();
                if (currentScore > lastScore) {
                    ScoresProperties.setPlayerScore(id, currentScore);
                }

                break;
        }
        pack();
    }

    public GameStateProvider(GameStates gameState) {

        GameStateProvider.gameState = gameState;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        //Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setLocation(d.width / 2 - this.getSize().width / 2, d.height / 2 - this.getSize().height / 2);
        setLocation(200, 200);
        setGameState(gameState);
    }

    @Override
    public void paintComponents(Graphics g) {
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        super.paintComponents(g);
    }
}
