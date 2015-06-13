package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionButton extends JButton {

    public ActionButton(String text, Color normalColor, Color hoverColor, Color clickColor, Font font) {
        final Color _normalColor = normalColor;
        final Color _hoverColor = hoverColor;
        final Color _clickColor = clickColor;

        setText(text);
        setForeground(normalColor);
        setFont(font);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setForeground(_clickColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setForeground(_hoverColor);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(_hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(_normalColor);
            }
        });
    }
}
