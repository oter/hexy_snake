package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static JFrame menuFrame;

    private static RendererPanel rendererPanel;

    private static SnakeScene scene = new SnakeScene();

    private static final Dimension ButtonSize = new Dimension(135, 35);

    public static void main(String[] args) {

        // enable anti-aliased text:
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");



       // menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JFrame frame = new JFrame("Hexy Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainMenu mainMenu = new MainMenu();
        frame.setContentPane(mainMenu);

        frame.setSize(mainMenu.getSize());
        frame.setMinimumSize(mainMenu.getSize());
        frame.setVisible(true);
        frame.setResizable(false);

//
//        menuframe.getContentPane().add(results);
        //menuframe.getContentPane().add(play);
        //menuframe.getContentPane().add(playButton, new Dimension(50, 50));


        //jframe.add(rendererPanel = new RendererPanel(scene));
        //jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Snake snake = new Snake();
    }
}
