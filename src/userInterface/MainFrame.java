package userInterface;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Runnable {
    private OverviewSwitchPanel overviewSwitchPanel;
    private SideMenu sideMenu;

    /**
     * This variable stores the instance of the mainFrame,
     * because if we call the mainframe we would like to get the current running instance instead of creating a new one.
     */
    private static MainFrame instance = null;

    /**
     * Get the instance of the mainFrame
     * @return MainFrame instance
     */
    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
        }
        return instance;
    }

    @Override
    public void run() {
        this.setTitle("Netflix Statistix");
        this.setSize(new Dimension(700, 700));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        createComponents(this.getContentPane());

        this.setVisible(true);
    }

    /**
     * Creates the components that will be showed on the screen (MainFrame)
     * @param container The contentPane from the {@link MainFrame}
     */
    private void createComponents(Container container) {
        this.setLayout(new BorderLayout());

        overviewSwitchPanel = new OverviewSwitchPanel();
        sideMenu = new SideMenu();
        Footer footer = new Footer();


        this.add(overviewSwitchPanel);
        container.add(sideMenu, BorderLayout.WEST);
        container.add(footer, BorderLayout.SOUTH);
    }

    /**
     * Get the OverviewSwitchPanel
     * @return OverviewSwitchPanel
     */
    public OverviewSwitchPanel getOverviewSwitchPanel() {
        return overviewSwitchPanel;
    }
}
