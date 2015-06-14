package Snake;


import javax.swing.*;
import java.awt.*;

public class SnakeProperties {

    private static final int polygonPoints = 6;

    private static final double polygonConstVal = Math.sqrt(3) / 2;

    public static final int betweenLen = 4;

    private static int ceilRadius = 30;

    public static Dimension gameFieldPos = new Dimension(60, 100);

    public static final Color levelLabelColor = new Color(0x0D818E);

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

    private static final Color snakeBaseColor = new Color(255, 0, 0);

    private static final Color brickFlag = new Color(255, 255, 0);

    private static final Color food1Color = new Color(0, 255, 0);

    public static final double getPolygonConstVal(){
        return polygonConstVal;
    }

    public static int getCeilRadius() {
        return ceilRadius;
    }

    public static int getPolygonPoints() {
        return polygonPoints;
    }

    public static final Color getSnakeNextColor(Color color) {
        return color == null ? snakeBaseColor : color.brighter();
    }

    public static final Color getColorByTag(TagType tag) {
        // System.out.println("Tag: " + String.valueOf(tag.getValue()));
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
