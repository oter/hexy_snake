package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEKTO on 18.04.2015.
 */
public class Main {

    private static int sizeX = 10;

    private static int sizeY = 10;

    private static JFrame jframe;

    private static JFrame menuframe;

    private static RendererPanel rendererPanel;

    private static SnakeScene scene = new SnakeScene(sizeX, sizeY);

    private static final Dimension ButtonSize = new Dimension(135, 35);


    /**
     * It's a point of starting program.
     * We create a main object of the game.
     */
    public static void main(String[] args) {

        JButton play ;

        //Container contain = menuframe.getContentPane();
        //contain.setLayout(new FlowLayout());


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


        JButton results = new JButton("RESULTS");
        results.setVisible(true);
        results.setLocation(202, 202);
        results.setPreferredSize(new Dimension(100,100));

        menuframe.getContentPane().add(results);
        menuframe.getContentPane().add(play);



        play.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jframe.add(rendererPanel = new RendererPanel(scene));
                jframe.setVisible(true);
                jframe.setSize(440, 440);
                jframe.setLocationRelativeTo(null);
                menuframe.setVisible(false);

            }

        });

        results.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jframe, "Don't touch me! It will be soon.", "Warning", JOptionPane.WARNING_MESSAGE);

            }

        });

        //jframe.getContentPane().add(results);


        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Snake snake = new Snake();



    }
}
