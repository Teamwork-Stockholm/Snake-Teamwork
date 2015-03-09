package game;

import display.Display;
import gfx.ImageLoader;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Game implements Runnable{
    private Display display;
    public int width, height;
    public String title;
    public static final int FIELD_LIMIT_X = 49;
    public static final int FIELD_LIMIT_Y = 36;
    public static Snake snake;
    public static Food food;
    public int x = 10, y = 10;

    private boolean isRunning;
    private Thread thread;

    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage img;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init(){
        display = new Display(this.width, this.height, this.title);
        snake = new Snake();
        food = new Food(FIELD_LIMIT_X,FIELD_LIMIT_Y);
        img = ImageLoader.loadImage("/images/background.png", this.width, this.height);
        inputHandler = new InputHandler(display);
    }

    @Override
    public void run() {
        init();
        while (isRunning) {
            render();
            snake.tick();
            try {
                Thread.sleep(80);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void tick(){

    }
    
    private void render(){
    	bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            //Create 2 buffers
            display.getCanvas().createBufferStrategy(2);
            bs = display.getCanvas().getBufferStrategy();
            //returns out of the method to prevent errors
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0,this.width,this.height);

        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        snake.drawSnake(g);
        food.drawFood(g);
        bs.show();
        g.dispose();
    }

    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this, "Game Loop");
        thread.start();
    }
    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
