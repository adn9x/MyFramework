package GameComponent;

/**
 * Created by ducanh97 on 1/19/2017.
 */


// Actions attach to an Actor and perform some task
// TODO: we will create some build-in action later such as gravity falling or throwing action for client

public abstract class Action {

    private int tickPerAct;
    private int tick = 0;

    public Action() {

        tickPerAct = initTickPerAct();
    }

    public abstract int initTickPerAct();

    public void setTickPerAct(int tickPerAct) {
        this.tickPerAct = tickPerAct;
    }

    public int getTickPerAct() {
        return tickPerAct;
    }


    // action() is template method called by tick() method of actor
    // we use "Template Method Pattern" here

    final public void action() {
        tick++;

        if (tick >= tickPerAct) {
            this.act();
            tick = 0;
        }
    }


    // This method is called base on tick per actions
    public abstract void act();

}
