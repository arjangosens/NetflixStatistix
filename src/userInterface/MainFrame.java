package userInterface;

import javafx.geometry.Side;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Runnable {
    private SideMenu sideMenu;

    @Override
    public void run() {
        this.setTitle("Netflix Statistix");
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(this.getContentPane());

        this.pack();
        this.setVisible(true);
    }

    private void createComponents(Container container) {
        this.setLayout(new BorderLayout());
        sideMenu = new SideMenu();
        Footer footer = new Footer();

        container.add(sideMenu, BorderLayout.WEST);
        container.add(footer, BorderLayout.SOUTH);
    }

}
