package Snake;

import java.awt.*;

/**
 * Created by NEKTO on 18.04.2015.
 */
public class SnakeScene {
    /**
     *  This is declaration sizeX X parameter of size
     */
    private int sizeX;
    /**
     *  This is declaration sizeY Y parameter of size
     */
    private int sizeY;
    /**
     * This is declaration matrix of HexCeil
     */
    private HexCeil ceilsMatrix[][];

    private LevelDescription levelDescription;

    public void setLevel(LevelDescription levelDescription) {
        this.levelDescription = levelDescription;
    }

    /**
     * This is class constructor.
     * @param sizeX X parameter of size
     * @param sizeY Y parameter of size
     */
    public SnakeScene(int sizeX, int sizeY) {
        setSize(sizeX, sizeY);
        createCeils();

        LevelLoader levelLoader = new LevelLoader();
        LevelDescription description = levelLoader.LoadLevel(2);
        setLevel(description);
        System.out.println("Levels count: " + String.valueOf(levelLoader.getLevelsCount()));
    }

    /**
     * It's function draw HexCeil like a ceil matrix on the frame.
     * @param g use for painting
     */
    public void redraw(Graphics g) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                ceilsMatrix[i][j].drawCeil(g, SnakeProperties.getColorByTag(levelDescription.getCellTag(i, j)));
            }
        }
    }

    /**
     * It's setter of parameters for HexCeil
     * @param sizeX size of X parameter
     * @param sizeY size of Y parameter
     */
    void setSize(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     *  This function creates HexCeil matrix and shifts series of HexCeil.
     *  We may use also this construction.
     *
     *  if ( j % 2 == 0) {
     *  shift = 0;
     *  } else
     *  {
     *  shift = HexCeil.getCeilRadius();
     *  }
     */
    void createCeils() {
        ceilsMatrix = new HexCeil[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                int shift = j % 2 == 0 ? 0 : HexCeil.getCeilRadius();
                ceilsMatrix[i][j] = new HexCeil(i * HexCeil.getCeilRadius() * 2 + shift, j * HexCeil.getCeilRadius() * 2);
            }
        }
    }

    /**
     * It's getter of sizeX parameter for HexCeil
     * @return sizeX X parameter of size
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * It's getter of sizeY parameter for HexCeil
     * @return sizeX Y parameter of size
     */
    public int getSizeY() {
        return sizeY;
    }


}
