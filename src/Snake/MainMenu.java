package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JPanel {

    private static final Dimension size = new Dimension(809, 730);

    private GameStateProvider gameStateProvider;

    private GameStateProvider getGameStateProvider() {
        return gameStateProvider;
    }


    public MainMenu(GameStateProvider gameStateProvider) {
        this.gameStateProvider = gameStateProvider;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(14, 1));
        setBackground(SnakeProperties.menuBackgroundColor);
        setMaximumSize(size);
        setSize(size);
        setPreferredSize(size);
        setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("WELCOME", SwingConstants.CENTER);
        int currentPlayerScore = ScoresProperties.getPlayerScore(ScoresProperties.getCurrentPlayerName());
        JLabel scoreLabel = new JLabel("Score: " + Integer.toString(currentPlayerScore), SwingConstants.CENTER);

        welcomeLabel.setFont(SnakeProperties.footerFont);
        welcomeLabel.setForeground(SnakeProperties.footerLabelColor);

        scoreLabel.setFont(SnakeProperties.playerFont);
        scoreLabel.setForeground(SnakeProperties.scoresLabelColor);


        Color hoverColor = SnakeProperties.hoverButtonColor;
        Color pressedColor = SnakeProperties.clickedButtonColor;

        ActionButton newPlayerButton = new ActionButton(ScoresProperties.getCurrentPlayerName(), SnakeProperties.playerButtonColor,
                pressedColor, hoverColor, SnakeProperties.playerFont);

        newPlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getGameStateProvider().setGameState(GameStates.NEW_PLAYER_MENU);
            }
        });

        ActionButton playButton = new ActionButton("PLAY", SnakeProperties.playButtonColor, pressedColor, hoverColor,
                SnakeProperties.buttonFont);

        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getGameStateProvider().setGameState(GameStates.PLAY_GAME);
            }
        });

        ActionButton scoresButton = new ActionButton("SCORES", SnakeProperties.scoresButtonColor, pressedColor,
                hoverColor, SnakeProperties.buttonFont);

        scoresButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getGameStateProvider().setGameState(GameStates.SCORES_MENU);
            }
        });

        scoresButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getGameStateProvider().setGameState(GameStates.SCORES_MENU);
            }
        });

        ActionButton emptyScoresButton = new ActionButton("EMPTY", SnakeProperties.emptyButtonColor, pressedColor,
                hoverColor, SnakeProperties.buttonFont);

        emptyScoresButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ScoresProperties.emptyScores();
            }
        });

        ActionButton exitButton = new ActionButton("EXIT", SnakeProperties.exitButtonColor, pressedColor, hoverColor,
                SnakeProperties.buttonFont);
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
        });

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(welcomeLabel);
        add(Box.createVerticalGlue());
        add(newPlayerButton);
        add(scoreLabel);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(playButton);
        add(scoresButton);
        add(emptyScoresButton);
        add(exitButton);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(SnakeProperties.menuImage, 0, 0, null);
    }
}
