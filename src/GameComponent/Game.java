package GameComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by ducanh97 on 1/14/2017....
 */

// Game is the most complex class in my framework
//  A game object have window, title, width and height. Each game has serveral states


public final class Game extends JPanel implements Runnable {

    public int gameHeight;
    public int gameWidth;

    private boolean isRunning = true;
    private JFrame window;
    private State currentState;
    private HashMap<String, State> stateHashMap;


    public Game(String title, int width, int height) {

        initGui(title, width, height);

        new Thread(this).start();

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


    private void tick() {

        if (currentState == null) {
            return;
        }
        currentState.tick();
    }

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gameWidth, gameHeight);

        if (currentState == null) {
            return;
        }

        currentState.render((Graphics2D)g);
    }

    @Override
    public void run() {

        while (isRunning) {

            tick();
            repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
