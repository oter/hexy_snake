package Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Snake {

    private int snakeDirection = 0;

    private Deque<SnakeCell> snakeBody = new ArrayDeque<SnakeCell>();

    private LevelDescription levelDescription;

    public void changeDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                snakeDirection++;
                if (snakeDirection > 5) {
                    snakeDirection = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
                snakeDirection--;
                if (snakeDirection < 0) {
                    snakeDirection = 5;
                }
                break;
            case KeyEvent.VK_UP:
                snakeEat();
                break;
            default:
                break;
        }
    }

    public int getSnakeX() {
        return snakeBody.getFirst().getX();
    }

    public int getSnakeY() {
        return snakeBody.getFirst().getY();
    }

    public void snakeEat() {
        updateSnake(getSnakeX(), getSnakeY());
    }

    public void snakeMove() {
        int x = getSnakeX();
        int y = getSnakeY();
        switch (snakeDirection) {

            case 0:
                x--;
                break;
            case 1:
                if (y % 2 == 0) {
                    x--;
                }
                y++;
                break;
            case 2:
                if (y % 2 == 0) {

                    y++;
                } else
                {
                    x++;
                    y++;
                }
                break;
            case 3:
                x++;
                break;
            case 4:
                if (y % 2 != 0) {
                    x++;
                }
                y--;
                break;
            case 5:
                if (y % 2 == 0) {
                    x--;
                    y--;
                } else
                {
                    y--;
                }
                break;
            default:
                break;
        }
        updateHead(x, y);
        // TODO: Snake collisions, snake eating
    }

    public void redraw(Graphics g) {
        Iterator<SnakeCell> iterator = snakeBody.iterator();
        while (iterator.hasNext()) {
            SnakeCell cell = iterator.next();
            cell.drawCeil(g, Color.RED);
            System.out.println("Draw: " + Integer.toString(cell.getX()) + ":" + Integer.toString(cell.getY()));
        }
    }

    private void updateSnake(int xi, int yi) {
        int ceilsRadius = SnakeProperties.getCeilRadius();
        double polygonVal = SnakeProperties.getPolygonConstVal();
        int betweenLen = SnakeProperties.betweenLen;

        double ceilsWidth = ceilsRadius * polygonVal + betweenLen;
        double ceilHalf = ceilsRadius * polygonVal;

        int x = (int)Math.round(ceilsWidth * xi * 2 + ceilsWidth * (yi % 2 ));
        int y = (int)Math.round((yi * 2 * (ceilHalf * polygonVal - betweenLen / 2 * polygonVal + 1 / polygonVal * betweenLen)));

        snakeBody.push(new SnakeCell(xi, yi, x + SnakeProperties.gameFieldPos.width, y + SnakeProperties.gameFieldPos.height));
    }

    public void updateHead(int x, int y) {
        if (snakeBody.size() > 0) {
            snakeBody.removeLast();
        }
        updateSnake(x, y);
    }

    public Snake(LevelDescription levelDescription, int x, int y){
        this.levelDescription = levelDescription;
        updateSnake(x, y);
        snakeDirection = 3;
    }
}
