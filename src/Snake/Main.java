package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static final JFrame gameFrame = new JFrame("Hexy Snake");

    private static SnakeScene scene = new SnakeScene();

    private static final Dimension ButtonSize = new Dimension(135, 35);

    public static void main(String[] args) {

        // enable anti-aliased text:
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");

        GameStateProvider gameStateProvider = new GameStateProvider(GameStates.MAIN_MENU);


        //jframe.add(rendererPanel = new RendererPanel(scene));
        //jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Snake snake = new Snake();
    }
}
