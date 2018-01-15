package userInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeScreen extends JPanel {

    public HomeScreen() {
        // Set the GridBagLayout for this JPanel.
        this.setLayout(new GridBagLayout());

        // Create new GridBagConstraints for this panel.
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel title = new JLabel("Netflix Statistix");
        title.setFont(new Font("Serif", Font.PLAIN, 30));

        // Set the constraints variables that are needed to get a nice look.
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        // Add the title with the contraints to the panel.
        this.add(title, constraints);

        try {
            BufferedImage image = ImageIO.read(new File("./img/netflixLogo.jpg"));
            JLabel imageLabel = new JLabel(new ImageIcon(image));

            this.add(imageLabel, constraints);
        } catch (IOException e) {
            System.out.println(e);
        }}
    }

