package userInterface;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel{

    public Footer() {
        // Set a gridLayout to get a nice and even divided footer
        this.setLayout(new GridLayout(1, 5));

        // Create the gui-components
        createComponents();
    }

    /**
     * Creates the components that will be displayed in the footer.
     */
    private void createComponents() {
        // Create labels with team information
        JLabel projectName = new JLabel("Netflix Statistix");
        JLabel courseName = new JLabel("Trio opdracht");
        JLabel courseYear = new JLabel("2017/2018");
        JLabel className = new JLabel("23IVT1B");
        JLabel studentNames = new JLabel("Arjan Gosens, Niek Flipse, Sam Glerum");

        // Add team information to the Footer
        this.add(projectName);
        this.add(courseName);
        this.add(courseYear);
        this.add(className);
        this.add(studentNames);
    }

}
