package GameComponent;

import javax.swing.*;

/**
 * Created by ducanh97 on 1/14/2017.
 */


public class Window extends JFrame {

    public Window(String title, JPanel game) {

        setResizable(false);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(game);
        pack();
        setLocationRelativeTo(null);
        setFocusable(true);
        requestFocusInWindow();
    }
}
