package Snake;


import javax.swing.*;
import java.awt.*;

public class NewPlayerMenu extends JPanel {

    private static final Image menuImage = new ImageIcon("resources/menu/MenuImage_800x700.png").getImage();

    private static final Dimension size = new Dimension(809, 730);

    JTextField newUserField = new JTextField("Player");

    public void focusUserField() {
        newUserField.requestFocusInWindow();
        newUserField.selectAll();
    }


    public NewPlayerMenu() {
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


        newUserField.setOpaque(false);
        newUserField.setBorder(BorderFactory.createEmptyBorder());

        newUserField.setFont(SnakeProperties.playerFont);
        newUserField.setSelectedTextColor(SnakeProperties.playerButtonColor);
        newUserField.setForeground(SnakeProperties.playerButtonColor);
        newUserField.setHorizontalAlignment(JTextField.CENTER);

        Color hoverColor = SnakeProperties.hoverButtonColor;
        Color pressedColor = SnakeProperties.clickedButtonColor;

        ActionButton okButton = new ActionButton("OK", SnakeProperties.playButtonColor,
                pressedColor, hoverColor, SnakeProperties.buttonFont);


        ActionButton cancelButton = new ActionButton("CANCEL", SnakeProperties.scoresButtonColor,
                pressedColor, hoverColor, SnakeProperties.buttonFont);

        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

            }
        });

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(newPlayerLabel);
        add(Box.createVerticalGlue());
        add(newUserField);

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
