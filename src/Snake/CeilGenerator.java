package Snake;

public class CeilGenerator {
    private int hexPointsX[];
    private int hexPointsY[];

    public CeilGenerator(int n, int ceilRadius) {
        hexPointsX = new int[n];
        hexPointsY = new int[n];
        for (int i = 0; i < n; i++) {
            hexPointsX[i] = (int)Math.round(ceilRadius * Math.sin(((double)i / n) * 2 * Math.PI) + ceilRadius);
            hexPointsY[i] = (int) Math.round(ceilRadius * Math.cos(((double) i / n) * 2 * Math.PI) + ceilRadius);
        }
    }

    public int[] getPointsX() {
        return hexPointsX;
    }

    public int[] getPointsY() {
        return hexPointsY;
    }
}
