package applicationLogic;

import userInterface.MainFrame;
import userInterface.OverviewSwitchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the {@link ActionListener} for the CardLayout.
 */
public class OverviewSwitchController implements ActionListener {

    /**
     * The name of the nextPanel that will be viewed on the screen.
     */
    private String nextPanel;

    public OverviewSwitchController(String nextPanel) {
        this.nextPanel = nextPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // The variable osp stores the OverviewSwitchPanel object.
        OverviewSwitchPanel osp = MainFrame.getInstance().getOverviewSwitchPanel();

        // Now the method switchPanels is called. The nextPanel becomes visible here.
        osp.switchPanels(nextPanel);
    }
}
