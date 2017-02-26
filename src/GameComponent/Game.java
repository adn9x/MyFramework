package GameComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

/**
 * Created by ducanh97 on 1/14/2017....
 */

// Game is the most complex class in my framework
//  A game object have window, title, width and height. Each game has serveral states


public final class Game extends Canvas implements Runnable {

    public int gameHeight;
    public int gameWidth;

    private boolean isRunning = false;
    private JFrame window;
    private State currentState;
    private int tickPerSecond = -1;

    private HashMap<String, State> stateHashMap;


    public Game(String title, int width, int height, int tickPerSecond) {

        initGui(title, width, height);

        this.tickPerSecond = tickPerSecond;

        stateHashMap = new HashMap<>();
    }

    public void setCurrentState(String name) {
        State state = stateHashMap.get(name);
        if (state == null) { return; }
        currentState = state;
    }


    public void addState(String name, State state) {

        stateHashMap.put(name, state);
        state.setGame(this);
    }

    public void removeState(String name) {

        stateHashMap.remove(name);
    }

    public State getState(String name) {
        return stateHashMap.get(name);
    }

    private void initGui(String title, int width, int height) {

        this.gameWidth = width;
        this.gameHeight = height;

        setMaximumSize(new Dimension(gameWidth, gameHeight));
        setMinimumSize(new Dimension(gameWidth, gameHeight));
        setPreferredSize(new Dimension(gameWidth, gameHeight));

        window = new Window(title, this);
        requestFocus();
        window.setVisible(true);
    }

    public synchronized void start() {
        isRunning = true;
        new Thread(this).start();
    }


    private void tick() {

        if (currentState == null) {
            return;
        }
        currentState.tick();
    }

    public void render() {

        BufferStrategy bs = getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gameWidth, gameHeight);


        if (currentState != null) {
            currentState.render((Graphics2D)g);
        }

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / tickPerSecond;
        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (isRunning) {

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frames++;
            render();
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                frames = 0;
                ticks = 0;
            }
        }
    }
}
