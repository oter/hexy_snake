package Snake;

import sun.swing.SwingUtilities2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setForeground(_clickColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setForeground(_hoverColor);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setForeground(_hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setForeground(_normalColor);
            }
        });

    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        super.paintComponents(g);
    }
}
