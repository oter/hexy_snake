package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewPlayerMenu extends JPanel {

    private static final Image menuImage = new ImageIcon("resources/menu/MenuImage_800x700.png").getImage();

    private static final Dimension size = new Dimension(809, 730);

    private static GameStateProvider gameStateProvider;

    JTextField newPlayerField = new JTextField("Player");

    private static GameStateProvider getGameStateProvider() {
        return gameStateProvider;
    }

    public void focusUserField() {
        newPlayerField.requestFocusInWindow();
        newPlayerField.selectAll();
    }

    private final void okClick() {
        ScoresProperties.addNewPlayer(newPlayerField.getText());
        getGameStateProvider().setGameState(GameStates.MAIN_MENU);
    }

    public NewPlayerMenu(GameStateProvider gameStateProvider) {
        NewPlayerMenu.gameStateProvider = gameStateProvider;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(10, 1));
        setBackground(SnakeProperties.menuBackgroundColor);
        setMaximumSize(size);
        setSize(size);
        setPreferredSize(size);
        setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JLabel newPlayerLabel = new JLabel("NEW PLAYER", SwingConstants.CENTER);

        newPlayerLabel.setFont(SnakeProperties.footerFont);
        newPlayerLabel.setForeground(SnakeProperties.footerLabelColor);

        newPlayerField.setOpaque(false);
        newPlayerField.setBorder(BorderFactory.createEmptyBorder());
        newPlayerField.setFont(SnakeProperties.playerFont);
        newPlayerField.setSelectedTextColor(SnakeProperties.playerButtonColor);
        newPlayerField.setForeground(SnakeProperties.playerButtonColor);
        newPlayerField.setHorizontalAlignment(JTextField.CENTER);

        Color hoverColor = SnakeProperties.hoverButtonColor;
        Color pressedColor = SnakeProperties.clickedButtonColor;

        final ActionButton okButton = new ActionButton("OK", SnakeProperties.playButtonColor,
                pressedColor, hoverColor, SnakeProperties.buttonFont);

        newPlayerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    okClick();
                }
            }
        });

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                okClick();
            }
        });

        ActionButton cancelButton = new ActionButton("CANCEL", SnakeProperties.scoresButtonColor,
                pressedColor, hoverColor, SnakeProperties.buttonFont);

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getGameStateProvider().setGameState(GameStates.MAIN_MENU);
            }
        });

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(newPlayerLabel);
        add(Box.createVerticalGlue());
        add(newPlayerField);

        add(Box.createVerticalGlue());
        add(okButton);
        add(cancelButton);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, null);
    }
}
