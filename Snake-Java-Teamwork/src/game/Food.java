package game;


import java.awt.*;
import java.util.Random;

public class Food {

	int foodX,foodY;
	private Random randGenerator;

	public Food(int limitX,int limitY) {
		
		randGenerator = new Random();
		int x = randGenerator.nextInt(limitX);
		int y = randGenerator.nextInt(limitY);
		this.foodX = x;
		this.foodY = y;
	}
	Image food = Toolkit.getDefaultToolkit().createImage("resources/images/mouse.png");

	public void drawFood(Graphics g){
		int foodSize = SnakePart.TILE_SIZE;
		g.drawImage(food, this.foodX*foodSize, this.foodY*foodSize, foodSize, foodSize, null);
	}
}
