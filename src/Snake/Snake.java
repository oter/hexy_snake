package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Snake extends SnakeScene {

    private Timer snakeTimer;

    private boolean pauseGame = false;

    private void continueGame() {
        continueGameTimer();
        snakeTimer.start();
    }

    private void pauseGame() {
        pauseGameTimer();
        snakeTimer.stop();
    }

    protected void addNewFoodRandom(TagType t) {
        boolean tryPlace = true;
        while (tryPlace) {
            int x = (int)(Math.random() * getLevelDescription().getFieldSizeX());
            int y = (int)(Math.random() * getLevelDescription().getFieldSizeY());
            if (getLevelDescription().getCellTag(x, y) == TagType.EMPTY_TAG) { // check free space on field

                boolean inSnake = false;
                Iterator<SnakeCell> iterator = snakeBody.iterator(); // check if food is not in the snake
                while (iterator.hasNext()) {
                    SnakeCell cell = iterator.next();
                    if (cell.getX() == x && cell.getY() == y) {
                        inSnake = true;
                    }
                }
                if (!inSnake) {
                    tryPlace = false; // Place food in empty space
                    getLevelDescription().setCellTag(t, x, y);
                }
            }
        }
    }

    private void stopTimers() {
        stopTimer();
        snakeTimer.stop();
    }

    private boolean isChangeDirectionAllowed() {
        return changeDirectionAllowed;
    }

    private void setChangeDirectionAllowed(boolean changeDirection) {
        this.changeDirectionAllowed = changeDirection;
    }

    private boolean changeDirectionAllowed;

    private SnakeDirection direction;

    private Deque<SnakeCell> snakeBody = new ArrayDeque<SnakeCell>();

    private void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    private SnakeDirection getDirection() {
        return direction;
    }

    private boolean checkSnakeCollision(int headX, int headY) {
        Iterator<SnakeCell> iterator = snakeBody.iterator();
        while (iterator.hasNext()) {
            SnakeCell cell = iterator.next();
            if ((cell.getX() == headX) && (cell.getY() == headY)) {
                return true;
            }
        }
        return false;
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
        switch (getDirection()) {
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

    private void changeDirection(KeyEvent e) {
        if (isChangeDirectionAllowed()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    turnLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    turnRight();
                    break;
                default:
                    break;
            }
        }
        setChangeDirectionAllowed(false);
    }

    private int getSnakeHeadX() {
        return snakeBody.getFirst().getX();
    }

    private int getSnakeHeadY() {
        return snakeBody.getFirst().getY();
    }

    private void snakeEat() {
        updateSnake(getSnakeHeadX(), getSnakeHeadY());
    }

    private void snakeMove() {
        int x = getSnakeHeadX();
        int y = getSnakeHeadY();
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

        if (checkSnakeCollision(x, y)) {
            endGame();
        }

        switch (getLevelDescription().getCellTag(x, y)) {
            case EMPTY_TAG:
                updateHead(x, y);
                break;
            case FOOD_1_TAG:
                getLevelDescription().setCellTag(TagType.EMPTY_TAG, x, y);
                int score = getGameStateProvider().getScore();
                getGameStateProvider().setScore(score + 10);
                setAteTimes(getAteTimes() + 1);
                addNewFoodRandom(TagType.FOOD_1_TAG);
                snakeEat();
                if (getAteTimes() == getEatTimes()) {
                    stopTimers();
                    getGameStateProvider().setGameState(GameStates.NEXT_LEVEL);
                }

                updateHead(x, y);

                repaint();
                break;
            case BRICK_TAG:
                stopTimers();
                endGame();
                break;
            default:
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator<SnakeCell> iterator = snakeBody.iterator();
        Color headColor = SnakeProperties.getSnakeNextColor(null);
        while (iterator.hasNext()) {
            SnakeCell cell = iterator.next();
            cell.drawCeil(g, headColor);
            headColor = SnakeProperties.getSnakeNextColor(headColor);
        }
    }

    private void updateSnake(int xi, int yi) {
        int ceilsRadius = getLevelDescription().getSnakeCellRadius();
        double polygonVal = SnakeProperties.getPolygonConstVal();
        int betweenLen = SnakeProperties.betweenLen;

        double ceilsWidth = ceilsRadius * polygonVal + betweenLen;
        double ceilHalf = ceilsRadius * polygonVal;

        int x = (int)Math.round(ceilsWidth * xi * 2 + ceilsWidth * (yi % 2 ));
        int y = (int)Math.round((yi * 2 * (ceilHalf * polygonVal - betweenLen / 2 * polygonVal + 1 / polygonVal * betweenLen)));

        snakeBody.push(new SnakeCell(getLevelDescription().getSnakeCellRadius(), xi, yi, x + SnakeProperties.gameFieldPos.width, y + SnakeProperties.gameFieldPos.height));
    }

    public void updateHead(int x, int y) {
        if (snakeBody.size() > 0) {
            snakeBody.removeLast();
        }
        updateSnake(x, y);
    }

    public Snake(GameStateProvider gameStateProvider, int level){
        super(gameStateProvider, level);
        pauseGame = false;

        direction = SnakeDirection.SNAKE_RIGHT;
        updateSnake(getLevelDescription().getSnakeX(), getLevelDescription().getSnakeY());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                changeDirection(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    stopTimers();
                    getGameStateProvider().setGameState(GameStates.GAME_OVER_MENU);
                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    pauseGame = !pauseGame;
                    if (pauseGame) {
                        pauseGame();
                    } else {
                        continueGame();
                    }
                }
            }
        });

        snakeTimer = new Timer(1, new ActionListener() {
            int snakeTicks = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                snakeTicks++;
                if (snakeTicks > (int) ((double) 1 / getLevelDescription().getSnakeSpeed() * 500)) {
                    snakeTicks = 0;
                    snakeMove();
                    repaint();
                }
                setChangeDirectionAllowed(true);
            }
        });
        snakeTimer.setRepeats(true);
        snakeTimer.start();
    }
}
