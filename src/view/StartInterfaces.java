package view;

import javax.swing.*;

public class StartInterfaces {

    static JFrame initialFrame = new JFrame("HTMLCreator");
    public static void startApp() {

        initialFrame.setContentPane(new CreatorInterface().bodyPanel);
        initialFrame.pack();
        initialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialFrame.setSize(650, 850);
        initialFrame.setVisible(true);
        initialFrame.setLocationRelativeTo(null);
    }
}
