package GameComponent;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ducanh97 on 1/15/2017.
 */

// An instance of Stage is used as manager of actors, stage is also Actor so we can use one stage to manage other stages

public class Stage extends Actor {

    private final ArrayList<Actor> actors = new ArrayList<>();

    public void addActor(Actor o) {
        actors.add(o);
        o.setStage(this);
    }

    public boolean removeActor(Actor o) {

        if (o == null) {
            return false;
        }

        return actors.remove(o);
    }

    // update logic all the actor this stage manages
    public void tick() {

        for (int i = 0; i < actors.size(); i++) {
            actors.get(i).tick();
        }
    }

    // render all the actor which this stage manages
    public void render(Graphics2D g) {

        for (int i = 0; i < actors.size(); i++) {
            actors.get(i).render(g);
        }
    }
}
