package Snake;
import javax.swing.*;
import java.awt.*;

public class HexCeil extends JComponent {

    private static CeilGenerator ceilGenerator;

    private Polygon polygon;

    private int posX;

    private int posY;

    public HexCeil(int cellRadius, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        ceilGenerator = new CeilGenerator(SnakeProperties.getPolygonPoints(), cellRadius);
        createPolygon();
    }

    private void createPolygon() {
        polygon = new Polygon();
        for (int i = 0; i < SnakeProperties.getPolygonPoints(); i++) {
            polygon.addPoint(ceilGenerator.getPointsX()[i] + posX, ceilGenerator.getPointsY()[i] + posY);
        }
    }
    protected void drawCeil(Graphics g, Color color) {
        g.setColor(color);
        g.fillPolygon(polygon);
        g.setColor(new Color(Color.BLACK.getRGB()));
        g.drawPolygon(polygon);
    }
}
