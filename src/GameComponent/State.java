package GameComponent;


import java.awt.*;

/**
 * Created by ducanh97 on 2/3/2017.
 */

// We use "State Pattern" to divide game to multiple state such as Menu, GamePlay, GameOver and so on
// we will inject it to Game object

public abstract class State {

    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract void tick();

    public abstract void render(Graphics2D g);

}
