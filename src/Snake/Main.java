package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void printCells(int w, int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                //Cell.0.9.tag=1
                System.out.println("Cell." + Integer.toString(i) + "." + Integer.toString(j) + ".tag=0");
            }
        }
    }

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        GameStateProvider gameStateProvider = new GameStateProvider(GameStates.MAIN_MENU);

        //printCells(30, 30);
    }
}
