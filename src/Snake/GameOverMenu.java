package Snake;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOverMenu extends JPanel {

    private static final Dimension size = new Dimension(809, 730);

    private static GameStateProvider gameStateProvider;

    private static GameStateProvider getGameStateProvider() {
        return gameStateProvider;
    }

    public GameOverMenu(GameStateProvider gameStateProvider, int score) {
        GameOverMenu.gameStateProvider = gameStateProvider;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(15, 1));
        setBackground(SnakeProperties.menuBackgroundColor);
        setMaximumSize(size);
        setSize(size);
        setPreferredSize(size);
        setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Score: " + Integer.toString(score), SwingConstants.CENTER);

        gameOverLabel.setFont(SnakeProperties.footerFont);
        gameOverLabel.setForeground(SnakeProperties.footerLabelColor);

        scoreLabel.setFont(SnakeProperties.playerFont);
        scoreLabel.setForeground(SnakeProperties.scoresLabelColor);

        ActionButton okButton = new ActionButton("OK", SnakeProperties.playButtonColor,
                SnakeProperties.clickedButtonColor, SnakeProperties.hoverButtonColor, SnakeProperties.buttonFont);

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getGameStateProvider().setGameState(GameStates.MAIN_MENU);
            }
        });

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(gameOverLabel);
        add(Box.createVerticalGlue());
        add(scoreLabel);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(okButton);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(SnakeProperties.menuImage, 0, 0, null);
    }
}