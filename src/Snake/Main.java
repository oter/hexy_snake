package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static JFrame jframe;

    private static JFrame menuframe;

    private static RendererPanel rendererPanel;

    private static SnakeScene scene = new SnakeScene();

    private static final Dimension ButtonSize = new Dimension(135, 35);

    public static void main(String[] args) {

        JButton play;


        jframe = new JFrame("HEXY SNAKE");
        jframe.setVisible(false);
        jframe.setSize(440, 440);
        jframe.setLocationRelativeTo(null);

        menuframe = new JFrame("MENU HEXY SNAKE");
        menuframe.setVisible(true);
        menuframe.setSize(440, 440);
        menuframe.setLocationRelativeTo(null);
        menuframe.setDefaultCloseOperation(menuframe.EXIT_ON_CLOSE);


        play = new JButton("PLAY");
        play.setVisible(true);
        play.setLocation(100, 50);
        play.setPreferredSize(new Dimension(100, 100));
        //contain.add(play);


//        JButton results = new JButton("RESULTS");
//        results.setVisible(true);
//        results.setLocation(202, 202);
//        results.setPreferredSize(new Dimension(100,100));
//
//        menuframe.getContentPane().add(results);
        menuframe.getContentPane().add(play);



        play.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                scene.loadLevel(2);
                jframe.add(rendererPanel = new RendererPanel(scene));
                jframe.setVisible(true);
                jframe.setSize(440, 440);
                jframe.setLocationRelativeTo(null);
                menuframe.setVisible(false);

            }

        });


        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Snake snake = new Snake();
    }
}
