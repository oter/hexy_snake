package Snake;

/**
 * Created by NEKTO on 18.04.2015.
 */
public class CeilGenerator {
    private int hexPointsX[];
    private int hexPointsY[];

    /**
     * It's create points of Hex
     * @param n number of edge figure
     * @param ceilRadius radius of Hex
     */
    public CeilGenerator(int n, int ceilRadius) {
        hexPointsX = new int[n];
        hexPointsY = new int[n];
        for (int i = 0; i < n; i++) {
            hexPointsX[i] = (int) (ceilRadius * Math.sin(((double)i / n) * 2 * Math.PI) + ceilRadius);
            hexPointsY[i] = (int) (ceilRadius * Math.cos(((double)i / n) * 2 * Math.PI) + ceilRadius);
        }
    }

    public int[] getPointsX() {
        return hexPointsX;
    }

    public int[] getPointsY() {
        return hexPointsY;
    }
}
