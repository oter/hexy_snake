package Snake;


import javax.swing.*;

public class GameStateProvider extends JFrame {

    private static final ScoresProperties scoresProperties = new ScoresProperties();

    private static GameStates gameState;

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
            case PLAY_MENU:

                break;
            case SCORES_MENU:
                ScoresMenu scoresMenu = new ScoresMenu(this);
                setContentPane(scoresMenu);
                setSize(scoresMenu.getSize());
                setMinimumSize(scoresMenu.getSize());
                break;
            case GAME_OVER_MENU:
                GameOverMenu gameOverMenu = new GameOverMenu(this, 100500);
                setContentPane(gameOverMenu);
                setSize(gameOverMenu.getSize());
                setMinimumSize(gameOverMenu.getSize());
                break;
        }
    }

    public GameStateProvider(GameStates gameState) {
        GameStateProvider.gameState = gameState;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        setGameState(gameState);
    }
}
