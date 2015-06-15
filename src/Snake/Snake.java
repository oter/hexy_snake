package Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Snake {

    private SnakeDirection direction;

    public GameStateProvider getGameStateProvider() {
        return gameStateProvider;
    }

    private GameStateProvider gameStateProvider;

    private int currentScore;

    private Deque<SnakeCell> snakeBody = new ArrayDeque<SnakeCell>();

    private LevelDescription levelDescription;

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void turnLeft() {
        switch (direction) {
            case SNAKE_LEFT:
                setDirection(SnakeDirection.SNAKE_DOWN_LEFT);
                break;
            case SNAKE_DOWN_LEFT:
                setDirection(SnakeDirection.SNAKE_DOWN_RIGHT);
                break;
            case SNAKE_DOWN_RIGHT:
                setDirection(SnakeDirection.SNAKE_RIGHT);
                break;
            case SNAKE_RIGHT:
                setDirection(SnakeDirection.SNAKE_UP_RIGHT);
                break;
            case SNAKE_UP_RIGHT:
                setDirection(SnakeDirection.SNAKE_UP_LEFT);
                break;
            case SNAKE_UP_LEFT:
                setDirection(SnakeDirection.SNAKE_LEFT);
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case SNAKE_LEFT:
                setDirection(SnakeDirection.SNAKE_UP_LEFT);
                break;
            case SNAKE_DOWN_LEFT:
                setDirection(SnakeDirection.SNAKE_LEFT);
                break;
            case SNAKE_DOWN_RIGHT:
                setDirection(SnakeDirection.SNAKE_DOWN_LEFT);
                break;
            case SNAKE_RIGHT:
                setDirection(SnakeDirection.SNAKE_DOWN_RIGHT);
                break;
            case SNAKE_UP_RIGHT:
                setDirection(SnakeDirection.SNAKE_RIGHT);
                break;
            case SNAKE_UP_LEFT:
                setDirection(SnakeDirection.SNAKE_UP_RIGHT);
                break;
        }
    }

    public void changeDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                turnLeft();
                break;
            case KeyEvent.VK_RIGHT:
                turnRight();
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
        switch (direction) {
            case SNAKE_LEFT:
                x--;
                break;
            case SNAKE_DOWN_LEFT:
                if (y % 2 == 0) {
                    x--;
                }
                y++;
                break;
            case SNAKE_DOWN_RIGHT:
                if (y % 2 != 0) {
                    x++;
                }
                y++;
                break;
            case SNAKE_RIGHT:
                x++;
                break;
            case SNAKE_UP_RIGHT:
                if (y % 2 != 0) {
                    x++;
                }
                y--;
                break;
            case SNAKE_UP_LEFT:
                if (y % 2 == 0) {
                    x--;
                }
                y--;
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
        }
    }

    private void updateSnake(int xi, int yi) {
        int ceilsRadius = levelDescription.getSnakeCellRadius();
        double polygonVal = SnakeProperties.getPolygonConstVal();
        int betweenLen = SnakeProperties.betweenLen;

        double ceilsWidth = ceilsRadius * polygonVal + betweenLen;
        double ceilHalf = ceilsRadius * polygonVal;

        int x = (int)Math.round(ceilsWidth * xi * 2 + ceilsWidth * (yi % 2 ));
        int y = (int)Math.round((yi * 2 * (ceilHalf * polygonVal - betweenLen / 2 * polygonVal + 1 / polygonVal * betweenLen)));

        snakeBody.push(new SnakeCell(levelDescription.getSnakeCellRadius(), xi, yi, x + SnakeProperties.gameFieldPos.width, y + SnakeProperties.gameFieldPos.height));
    }

    public void updateHead(int x, int y) {
        if (snakeBody.size() > 0) {
            snakeBody.removeLast();
        }
        updateSnake(x, y);
    }

    public Snake(LevelDescription levelDescription, GameStateProvider gameStateProvider, int x, int y){
        this.gameStateProvider = gameStateProvider;
        this.levelDescription = levelDescription;
        direction = SnakeDirection.SNAKE_RIGHT;
        updateSnake(x, y);
    }
}
