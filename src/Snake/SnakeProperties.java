package Snake;


import java.awt.*;

public class SnakeProperties {

    private static final int polygonPoints = 6;

    private static final double polygonConstVal = Math.sqrt(3) / 2;

    private static final int betweenLen = 4;

    private static int ceilRadius = 20;

    private static final Color backgroundColor = new Color(244, 164, 96);

    private static final Color emptyTagColor = new Color(255, 218, 185);

    private static final Color snakeBaseColor = new Color(255, 0, 0);

    private static final Color brickFlag = new Color(255, 255, 0);

    private static final Color food1Color = new Color(0, 255, 0);

    public static final int getBetweenLen() {
        return betweenLen;
    }

    public static final double getPolygonConstVal(){
        return polygonConstVal;
    }

    public static int getCeilRadius() {
        return ceilRadius;
    }

    public static int getPolygonPoints() {
        return polygonPoints;
    }

    public static final Color getBackgroundColor() {
        return backgroundColor;
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
