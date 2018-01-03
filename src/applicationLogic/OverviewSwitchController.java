package applicationLogic;

import userInterface.MainFrame;
import userInterface.OverviewSwitchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverviewSwitchController implements ActionListener {

    // The name of the panel that is used in the CardLayout
    private String nextPanel;

    public OverviewSwitchController(String nextPanel) {
        this.nextPanel = nextPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OverviewSwitchPanel osp = MainFrame.getInstance().getOverviewSwitchPanel();
        osp.switchPanels(nextPanel);
    }
}
