package Snake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEKTO on 18.04.2015.
 */

public class RendererPanel extends JPanel {

    private SnakeScene scene;

    public RendererPanel(SnakeScene scene) {
        this.scene = scene;
    }

    //private static final Dimension ButtonSize = new Dimension(135, 35);

    private GridBagConstraints c;

    private GridBagLayout layout;

    //private JButton play;

    public RendererPanel(){

        this.layout = new GridBagLayout();

    }

/*
 private void setButtons() {
        this.play.setPreferredSize(ButtonSize);
        this.layout.setConstraints(this.play, this.c);

    }
*/
/**
    //private ImageIcon background;

    private static final Dimension ButtonSize = new Dimension(135, 35);

    private JButton play;

    private JButton highscores;

    private JButton options;

    private JButton instructions;

    private JButton exit;

    //private JButton back;

    //private JButton ok;

    public RendererPanel() {
        this.play = new JButton("PLAY");
        this.highscores = new JButton("HIGHSCORES");
        this.options = new JButton("OPTIONS");
        this.instructions = new JButton("INSTRUCTION");
        this.highscores = new JButton("HIGHSCORES");
        this.exit = new JButton("EXIT");
        //this.back = new JButton("BACK");
        //this.ok = new JButton("OK");

        this.play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //RendererPanel.this.Click();
                RendererPanel.this.removeAll();
                RendererPanel.this.repaint();
                RendererPanel.this.revalidate();
                RendererPanel.this.Instructions();
            }
        });

        this.instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //RendererPanel.this.Click();
                RendererPanel.this.removeAll();
                RendererPanel.this.repaint();
                RendererPanel.this.revalidate();
                RendererPanel.this.Instructions();
            }
        });
    }


    private void Instructions() {
        //this.background = new ImageIcon("img//ins.jpg");
        String[] opis = new String[]{"Instruction will be", "Soon"};
        JLabel[] o = new JLabel[9];

        for(int i = 0; i < 9; ++i) {
            o[i] = new JLabel(opis[i]);
            o[i].setFont(new Font("Tahoma", 1, 14));
            o[i].setForeground(Color.black);
            this.add(o[i]);
            //this.c.gridwidth = 0;
            //this.layout.setConstraints(o[i], this.c);
        }

        //this.add(this.back);
    }
*/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawRect(0, 0, super.getWidth(), super.getHeight());
        g.setColor(SnakeProperties.getBackgroundColor());
        g.fillRect(0, 0, super.getWidth(), super.getHeight());

        scene.redraw(g);


        //if(this.background != null) {
        //    g.drawImage(this.background.getImage(), 0, 0, this.getWidth(), this.getHeight(), (ImageObserver)null);
        //}

    }

    public SnakeScene getScene() {
        return scene;
    }

    public void setScene(SnakeScene scene) {
        this.scene = scene;
    }
}
