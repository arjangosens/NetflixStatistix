import userInterface.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        SwingUtilities.invokeLater(mainFrame);
    }
}
