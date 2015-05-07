package Snake;
import javax.swing.*;
import java.awt.*;

/**
 * Created by NEKTO on 18.04.2015.
 */
public class HexCeil extends JComponent {
    private static final int n = 6;

    private static int ceilRadius = 20;

    public static int getCeilRadius() {
        return ceilRadius;
    }

    private static final CeilGenerator ceilGenerator = new CeilGenerator(n, ceilRadius);

    private Color fillColor;

    private Color borderColor;

    private Polygon polygon;

    private int posX;

    private int posY;

    /**
     * Constructor for HexCeil, creates polygon for rendering ceil.
     * @param posX X position
     * @param posY Y position
     */
    public HexCeil(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        createPolygon();
    }

    private void createPolygon() {
        polygon = new Polygon();
        for (int i = 0; i < n; i++) {
            polygon.addPoint(ceilGenerator.getPointsX()[i] + posX, ceilGenerator.getPointsY()[i] + posY);
        }
    }

    /**
     * It's paint Ceil
     * @param g use for painting
     */
    protected void drawCeil(Graphics g, Color color) {
        g.setColor(color);
        g.fillPolygon(polygon);
    }
}
