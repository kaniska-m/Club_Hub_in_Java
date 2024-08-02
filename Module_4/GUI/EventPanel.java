package GUI;

import javax.swing.*;
import java.awt.*;
import models.Event;

public class EventPanel extends JPanel {
    private JCheckBox bookmarkCheckBox;

    public EventPanel(Event event) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabel titleLabel = new JLabel(event.getTitle());
        JLabel dateLabel = new JLabel(event.getDate().toString());
        JTextArea descriptionArea = new JTextArea(event.getDescription());
        descriptionArea.setEditable(false);
        JLabel locationLabel = new JLabel("Location: " + event.getLocation()); // Display location
        JLabel clubLabel = new JLabel("Club: " + event.getClub().getName());

        bookmarkCheckBox = new JCheckBox("Bookmark");

        add(titleLabel, BorderLayout.NORTH);
        add(descriptionArea, BorderLayout.CENTER);
        add(clubLabel, BorderLayout.EAST);
        add(bookmarkCheckBox, BorderLayout.WEST);

        // Create a panel to hold date and location labels at the bottom
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(dateLabel, BorderLayout.WEST);
        bottomPanel.add(locationLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public boolean isBookmarked() {
        return bookmarkCheckBox.isSelected();
    }
}
