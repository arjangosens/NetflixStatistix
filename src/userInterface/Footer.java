package userInterface;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel{

    public Footer() {
        // Create the components
        createComponents();
    }

    /**
     * Creates the components that will be displayed in the footer.
     */
    private void createComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel projectName = new JLabel("Netflix Statistix");

        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(projectName, constraints);

        JLabel courseName = new JLabel("Trio opdracht");

//        constraints.insets = new Insets(5, 5, 5, 5);
//        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(courseName, constraints);

        JLabel courseYear = new JLabel("2017/2018");

//        constraints.insets = new Insets(5, 5, 5, 5);
//        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(courseYear, constraints);

        JLabel className = new JLabel("23IVT1B");
//        constraints.insets = new Insets(5, 5, 5, 5);
//        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(className, constraints);

        JLabel studentNames = new JLabel("Arjan Gosens, Niek Flipse, Sam Glerum");
//        constraints.insets = new Insets(5, 5, 5, 5);
//        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 5;
        constraints.gridy = 0;
        constraints.gridwidth = 3;

        this.add(studentNames, constraints);
    }

}
