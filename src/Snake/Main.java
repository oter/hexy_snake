package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        System.setProperty("swing.aatext", "true");

        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        System.setProperty( "awt.useSystemAAFontSettings", "lcd" );

        GameStateProvider gameStateProvider = new GameStateProvider(GameStates.MAIN_MENU);


        //jframe.add(rendererPanel = new RendererPanel(scene));
        //jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Snake snake = new Snake();
    }
}
