package GameComponent;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ducanh97 on 1/15/2017...
 */


// All the other game object class should inrehit from this class
// an actor has a 2-dimension position, width, heigh and list of actions to peform
// an actor can attach to Stage for more manageable

public class Actor {

    private int x, y;
    private int width, height;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    private ArrayList<Action> actions = new ArrayList<>();

    public Actor() {}

    public Actor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) { this.height = height; }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void removeAction(Action action) {
        actions.remove(action);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    // we can override this method to get bound of actor :D
    public Rectangle getBound() {
        return null;
    }


    public void tick() {
        for (int i = 0; i < actions.size(); i++) {
            actions.get(i).action();
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < actions.size(); i++) {
            actions.get(i).action();
        }
    }
}
