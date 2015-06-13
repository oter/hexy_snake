package Snake;


import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class ScoresMenu extends JPanel {

    private static final Dimension size = new Dimension(809, 730);

    private static GameStateProvider gameStateProvider;

    private static GameStateProvider getGameStateProvider() {
        return gameStateProvider;
    }

    public ScoresMenu(GameStateProvider gameStateProvider) {
        ScoresMenu.gameStateProvider = gameStateProvider;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(14, 1));
        setBackground(SnakeProperties.menuBackgroundColor);
        setMaximumSize(size);
        setSize(size);
        setPreferredSize(size);
        setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JLabel scoresLabel = new JLabel("SCORES", SwingConstants.CENTER);

        scoresLabel.setFont(SnakeProperties.footerFont);
        scoresLabel.setForeground(SnakeProperties.footerLabelColor);



        Color hoverColor = SnakeProperties.hoverButtonColor;
        Color pressedColor = SnakeProperties.clickedButtonColor;


        ActionButton cancelButton = new ActionButton("BACK", SnakeProperties.exitButtonColor,
                pressedColor, hoverColor, SnakeProperties.buttonFont);

        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getGameStateProvider().setGameState(GameStates.MAIN_MENU);
            }
        });

        add(Box.createVerticalGlue());
        add(scoresLabel);
        add(Box.createVerticalGlue());

        LinkedList<ScoreRecord> records = ScoresProperties.getScoresRecords();
        Iterator<ScoreRecord> iterator = records.iterator();

        char emptySymbol = ' ';
        FontMetrics metrics = getFontMetrics(SnakeProperties.scoresPlayerLabelFont);
        int emptySymbolWidth = metrics.charWidth(emptySymbol);


        final int leftTabulationSize = 20;
        char leftTabulation[] = new char[leftTabulationSize];
        for (int i = 0; i < leftTabulationSize; i++) {
            leftTabulation[i] = ' ';
        }

        int maxTabulationSize = 250 + metrics.stringWidth(new String(leftTabulation));

        for (int i = 0; i < SnakeProperties.scoreTablePlayersCount; i++) {
            StringBuffer buffer = new StringBuffer(100);
            buffer.append(leftTabulation, 0, 20);
            buffer.append(i + 1);
            buffer.append(". ");
            if (iterator.hasNext()) {
                ScoreRecord record = iterator.next();
                buffer.append(record.getPlayerName());

                int emptyWidth = maxTabulationSize - metrics.stringWidth(buffer.toString());
                int emptySymbolCount = Math.round(emptyWidth / emptySymbolWidth);

                for (int j = 0; j < emptySymbolCount; j++) {
                    buffer.append(' ');
                }
                buffer.append(record.getScore());

                JLabel scoresPlayerLabel = new JLabel(buffer.toString(), SwingConstants.LEFT);

                scoresPlayerLabel.setFont(SnakeProperties.scoresPlayerLabelFont);
                scoresPlayerLabel.setForeground(SnakeProperties.scoresPlayerLabelColor);

                add(scoresPlayerLabel);
            }
        }
        add(cancelButton);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(SnakeProperties.menuImage, 0, 0, null);
    }
}