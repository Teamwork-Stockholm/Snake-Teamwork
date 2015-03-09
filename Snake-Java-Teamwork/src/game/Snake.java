package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Collections;

public class Snake {
    public static LinkedList<SnakePart> body;
    public SnakePart head;

    public boolean isGameOver = false;

    private int velX, velY; //velocity

    public Snake(){
        body = new LinkedList<>();
        Collections.addAll(body,
                new SnakePart(1, 1),
                new SnakePart(2, 1),
                new SnakePart(3, 1));

        head = body.peekLast();
        velX = 1;
        velY = 0;
    }

    public void drawSnake(Graphics g){
        for (SnakePart bodyPart : body) {
            //Draw the body of the snake
            g.setColor(new Color(191,178,77));
            g.fillRoundRect(bodyPart.x * bodyPart.TILE_SIZE, bodyPart.y * bodyPart.TILE_SIZE, bodyPart.TILE_SIZE, bodyPart.TILE_SIZE, 10, 10);

            //Draws the border of each box
            g.setColor(Color.black);
            g.drawRoundRect(bodyPart.x * bodyPart.TILE_SIZE, bodyPart.y * bodyPart.TILE_SIZE, bodyPart.TILE_SIZE, bodyPart.TILE_SIZE, 10, 10);
        }
    }

    public void tick(){
//    	System.out.println(body.peekLast().x);
//    	System.out.println(body.peekLast().y);
        boolean isOutOfBounds = false;
        boolean eatFood=false;
        isOutOfBounds = body.peekLast().x > Game.FIELD_LIMIT_X || body.peekLast().x < 0 || body.peekLast().y > Game.FIELD_LIMIT_Y || body.peekLast().y < 0;
        eatFood=((body.peekLast().x == Game.food.foodX)&& (body.peekLast().y == Game.food.foodY));

        if (isOutOfBounds) {
            isGameOver = true;
        } else {
            if (eatFood) {
                SnakePart newSnakePart = new SnakePart(body.peekLast().x + velX,body.peekLast().y + velY);
                body.removeFirst();
                body.add(newSnakePart);
                body.add(newSnakePart);
                Game.food = new Food(Game.FIELD_LIMIT_X,Game.FIELD_LIMIT_Y);
            } else {
                SnakePart newSnakePart = new SnakePart(body.peekLast().x + velX,body.peekLast().y + velY);
                body.removeFirst();
                body.add(newSnakePart);
            }
        }
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
