package Snake;

import javax.swing.*;
import java.awt.*;

public class SnakeProperties {

    public static final String levelsPropsFilename = "level_props.properties";

    public static final String levelPropsFilename = "level.properties";

    public static final String levelsStr = "levels";

    public static final String levelsCountKey = "levelsCount";

    public static final String cellKey = "Cell.";

    private static final int polygonPoints = 6;

    private static final double polygonConstVal = Math.sqrt(3) / 2;

    public static final int betweenLen = 4;

    public static Dimension gameFieldPos = new Dimension(60, 100);

    public static final Color levelLabelColor = new Color(0x17D2E9);

    public static final Font levelLabelFont = new Font("Curlz MT", Font.BOLD, 40);

    public static final int scoreTablePlayersCount = 8;

    public static final Image menuImage = new ImageIcon("resources/menu/MenuImage_800x700.png").getImage();

    public static final String scoresFileName = "scores.properties";

    public static final Color timerLabelColor = new Color(255, 0, 0);

    public static final Color ateTimeLabelColor = new Color(4, 153, 0);

    public static final Font playerFont = new Font("Curlz MT", Font.BOLD, 40);

    public static final Font buttonFont = new Font("Curlz MT", Font.BOLD, 40);

    public static final Font footerFont = new Font("Curlz MT", Font.BOLD, 50);

    public static final Font scoresPlayerLabelFont = new Font("Curlz MT", Font.BOLD, 35);

    public static final Color scoresPlayerLabelColor = new Color(0xF2BD36);

    public static final Color scoresLabelColor = new Color(0xF23E43);

    public static final Color footerLabelColor = new Color(0xBCAB66);

    public static final Color menuBackgroundColor = new Color(0x82B1DB);

    public static final Color playButtonColor = new Color(0x6578CA);
    public static final Color playerButtonColor = new Color(0x5753FF);
    public static final Color scoresButtonColor = new Color(0x44BE85);
    public static final Color emptyButtonColor = new Color(0xB65A6C);
    public static final Color exitButtonColor = new Color(0xC8856A);

    public static final Color hoverButtonColor = new Color(0x618BB4);

    public static final Color clickedButtonColor = new Color(0x98CEFF);

    public static final Color backgroundColor = new Color(244, 164, 96);

    private static final Color emptyTagColor = new Color(255, 218, 185);

    private static final Color snakeBaseColor = new Color(56, 29, 28);

    private static final Color brickFlag = new Color(151, 91, 34);

    private static final Color food1Color = new Color(0, 188, 10);

    public static final double getPolygonConstVal(){
        return polygonConstVal;
    }

    public static int getPolygonPoints() {
        return polygonPoints;
    }

    private static int getRandomSign() {
        return Math.random() > 0.5 ? -1 : 1;
    }

    private static int getColorComponent(int c) {
        int newComponent = c + (int)(Math.random() * 2 * getRandomSign()) + 4;
        if (newComponent < 0) {
            newComponent = 0;
        }
        if (newComponent > 255) {
            newComponent = 255;
        }
        return newComponent;
    }

    public static final Color getSnakeNextColor(Color color) {
        Color newColor;
        if (color == null) {
            newColor = snakeBaseColor;
        } else {
            newColor = new Color(getColorComponent(color.getRed()), getColorComponent(color.getGreen()), getColorComponent(color.getBlue()));
        }
        return newColor;
    }

    public static final Color getColorByTag(TagType tag) {
        switch (tag) {
            case EMPTY_TAG:
                return emptyTagColor;
            case BRICK_TAG:
                return brickFlag;
            case FOOD_1_TAG:
                return food1Color;
            default:
                return Color.WHITE;
        }
    }
}
