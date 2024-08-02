package GUI;

import javax.swing.*;
import models.*;

public class MainFrame extends JFrame {
    public MainFrame(ClubService clubService, EventService eventService) {
        setTitle("Club Hub");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Club Feed", new ClubFeedPanel(clubService, eventService));

        add(tabbedPane);

        setVisible(true);
    }
}
