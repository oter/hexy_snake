package Snake;


import javax.swing.*;
import java.awt.*;

public class GameStateProvider extends JFrame {

    private static final ScoresProperties scoresProperties = new ScoresProperties();

    private static GameStates gameState;

    private int currentLevel;

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    //public void setCurrentScore(int currentScore) {
     //   this.currentScore = currentScore;
    //}

    public void nextLevel() {

    }

    public static GameStates getGameState() {
        return gameState;
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
            case PAUSE_MENU:

                break;
            case PLAY_GAME:
                SnakeScene snakeScene = new SnakeScene(this, 1);
                setContentPane(snakeScene);
                Dimension screenSize = new Dimension(snakeScene.getScreenWidth(), snakeScene.getScreenHeight());
                setSize(screenSize);
                setMinimumSize(screenSize);
                snakeScene.focus();

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
