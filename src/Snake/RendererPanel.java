package Snake;

import javax.swing.*;
import java.awt.*;

public class RendererPanel extends JPanel {

    private SnakeScene scene;

    public RendererPanel(SnakeScene scene) {
        this.scene = scene;
    }

    private GridBagConstraints c;

    private GridBagLayout layout;

    //private JButton play;

    public RendererPanel(){

        this.layout = new GridBagLayout();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawRect(0, 0, super.getWidth(), super.getHeight());
        g.setColor(SnakeProperties.backgroundColor);
        g.fillRect(0, 0, super.getWidth(), super.getHeight());

        scene.redraw(g);
    }

    public SnakeScene getScene() {
        return scene;
    }

    public void setScene(SnakeScene scene) {
        this.scene = scene;
    }
}
