package userInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeScreen extends JPanel {

    public HomeScreen() {
        JLabel title = new JLabel("Netflix Statistix");
        title.setFont(new Font("Serif", Font.PLAIN, 30));

        this.add(title);

        try {
            BufferedImage image = ImageIO.read(new File("./img/netflixLogo.jpg"));
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            this.add(imageLabel);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
