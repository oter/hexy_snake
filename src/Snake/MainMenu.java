package Snake;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    private static final Image menuImage = new ImageIcon("resources/menu/MenuImage_800x700.png").getImage();

    private static final Dimension size = new Dimension(809, 730);


    public MainMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(11, 1));
        setBackground(SnakeProperties.getMenuBackgroundColor());
        setMaximumSize(size);
        setSize(size);
        setPreferredSize(size);

        setAlignmentX(JPanel.CENTER_ALIGNMENT);

        ActionButton playButton = new ActionButton("PLAY", SnakeProperties.getMenuBackgroundColor(),
                new Color(Color.red.getRGB()), new Color(Color.green.getRGB()), new Font("Times New Roman", Font.BOLD, 40));

        ActionButton newPlayerButton = new ActionButton("NEW PLAYER", new Color(Color.blue.getRGB()),
                new Color(Color.red.getRGB()), new Color(Color.green.getRGB()), new Font("Times New Roman", Font.BOLD, 40));

        ActionButton scoresButton = new ActionButton("SCORES", new Color(Color.blue.getRGB()),
                new Color(Color.red.getRGB()), new Color(Color.green.getRGB()), new Font("Times New Roman", Font.BOLD, 40));

        ActionButton exitButton = new ActionButton("EXIT", new Color(Color.blue.getRGB()),
                new Color(Color.red.getRGB()), new Color(Color.green.getRGB()), new Font("Times New Roman", Font.BOLD, 40));

        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("You clicked the button, using a MouseListenr");
            }
        });

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(playButton);
        add(Box.createVerticalGlue());
        add(newPlayerButton);
        add(Box.createVerticalGlue());
        add(scoresButton);
        add(Box.createVerticalGlue());
        add(exitButton);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, null);
    }
}
