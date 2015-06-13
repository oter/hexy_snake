package Snake;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    private static final Image menuImage = new ImageIcon("resources/menu/MenuImage_800x700.png").getImage();

    private static final Dimension size = new Dimension(809, 730);


    public MainMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(15, 1));
        setBackground(SnakeProperties.menuBackgroundColor);
        setMaximumSize(size);
        setSize(size);
        setPreferredSize(size);
        setAlignmentX(JPanel.CENTER_ALIGNMENT);



        JLabel welcomeLabel = new JLabel("WELCOME", SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Score: " + Integer.toString(ScoresProperties.getPlayerScores(0)), SwingConstants.CENTER);


        welcomeLabel.setFont(SnakeProperties.footerFont);
        welcomeLabel.setForeground(SnakeProperties.footerLabelColor);

        scoreLabel.setFont(SnakeProperties.playerFont);
        scoreLabel.setForeground(SnakeProperties.scoresLabelColor);


        Color hoverColor = SnakeProperties.hoverButtonColor;
        Color pressedColor = SnakeProperties.clickedButtonColor;

        ActionButton newPlayerButton = new ActionButton(ScoresProperties.getCurrentPlayerName(), SnakeProperties.playerButtonColor,
                pressedColor, hoverColor, SnakeProperties.playerFont);

        ActionButton playButton = new ActionButton("PLAY", SnakeProperties.playButtonColor,
                pressedColor, hoverColor, SnakeProperties.buttonFont);


        ActionButton scoresButton = new ActionButton("SCORES", SnakeProperties.scoresButtonColor,
                pressedColor, hoverColor, SnakeProperties.buttonFont);

        ActionButton exitButton = new ActionButton("EXIT", SnakeProperties.exitButtonColor,
                pressedColor, hoverColor, SnakeProperties.buttonFont);

        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
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
        add(Box.createVerticalGlue());
        add(scoresButton);
        add(Box.createVerticalGlue());
        add(exitButton);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, null);
    }
}
