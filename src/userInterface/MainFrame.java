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
        // Set the title of the window
        this.setTitle("Netflix Statistix");
        this.setSize(new Dimension(933, 700));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // This makes sure the windows spawns in the center of the screen
        this.setLocationRelativeTo(null);

        // Call createComponents(), which creates the gui-components
        createComponents(this.getContentPane());

        // This method call makes the windows unresizable
        this.setResizable(false);
        // This method call makes the window visible
        this.setVisible(true);
    }

    /**
     * Creates the components that will be showed on the screen (MainFrame)
     * @param container The contentPane from the {@link MainFrame}
     */
    private void createComponents(Container container) {
        // Set the layout of the container to BorderLayout
        this.setLayout(new BorderLayout());

        // Define a new overviewSwitchPanel
        overviewSwitchPanel = new OverviewSwitchPanel();
        // Define a new SideMenu
        sideMenu = new SideMenu();
        // Define a new Footer
        Footer footer = new Footer();

        // Add the overviewSwitchPanel to the the Mainframe
        this.add(overviewSwitchPanel);
        // Add the sideMenu to the left-side of the container
        container.add(sideMenu, BorderLayout.WEST);
        // Add the Footer to the bottom-side of the container
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
