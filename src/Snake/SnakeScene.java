package Snake;

import javax.swing.*;
import java.awt.*;

public class SnakeScene extends JPanel {

    private HexCeil ceilsMatrix[][];

    private LevelDescription levelDescription;

    public int getScreenWidth() {
        return levelDescription.getScreenWidth();
    }

    public int getScreenHeight() {
        return levelDescription.getScreenHeight();
    }

    public SnakeScene() {
    }

    public void setLevel(LevelDescription levelDescription) {
        this.levelDescription = levelDescription;
        createCeils();
    }

    void loadLevel(int level){
        LevelLoader levelLoader = new LevelLoader();
        LevelDescription description = levelLoader.LoadLevel(level);
        setLevel(description);
        System.out.println("Levels count: " + String.valueOf(levelLoader.getLevelsCount()));
        System.out.println("Level: " + String.valueOf(description.getLevelName()));
    }

    public void redraw(Graphics g) {
        for (int i = 0; i < levelDescription.getFieldSizeX(); i++) {
            for (int j = 0; j < levelDescription.getFieldSizeY(); j++) {
                ceilsMatrix[i][j].drawCeil(g, SnakeProperties.getColorByTag(levelDescription.getCellTag(i, j)));
            }
        }
    }

    void createCeils() {
        ceilsMatrix = new HexCeil[levelDescription.getFieldSizeX()][levelDescription.getFieldSizeY()];
        int ceilsRadius = SnakeProperties.getCeilRadius();
        double polygonVal = SnakeProperties.getPolygonConstVal();
        int betweenLen = SnakeProperties.betweenLen;

        double ceilHalf = ceilsRadius * polygonVal;


        for (int i = 0; i < levelDescription.getFieldSizeX(); i++) {
            for (int j = 0; j < levelDescription.getFieldSizeY(); j++) {
                ceilsMatrix[i][j] = new HexCeil(
                        (int)((ceilsRadius * polygonVal + betweenLen ) * i * 2 + (ceilsRadius * polygonVal + betweenLen) * (j % 2 )),
                        (int)(j * 2 * (ceilHalf * polygonVal - betweenLen / 2 * polygonVal + 1 / polygonVal * betweenLen)));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        //System.out.println("Paint!");

        g.drawRect(0, 0, getScreenWidth(), getScreenHeight());
        g.setColor(SnakeProperties.backgroundColor);
        g.fillRect(0, 0, getScreenWidth(), getScreenHeight());

        redraw(g);
    }
}
