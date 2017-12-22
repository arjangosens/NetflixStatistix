package userInterface;

import javax.swing.*;

public class SideMenu {
    private JButton subsOverviewBtn;
    private JButton profileOverviewBtn;
    private JButton tvShowOverviewBtn;
    private JButton filmOverviewBtn;

    public SideMenu(JButton subsOverviewBtn, JButton profileOverviewBtn, JButton tvShowOverviewBtn, JButton filmOverviewBtn) {
        this.subsOverviewBtn = subsOverviewBtn;
        this.profileOverviewBtn = profileOverviewBtn;
        this.tvShowOverviewBtn = tvShowOverviewBtn;
        this.filmOverviewBtn = filmOverviewBtn;
    }
}
