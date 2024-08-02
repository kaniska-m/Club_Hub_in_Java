package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.Club;
import models.Event;
import models.Content;
import models.ClubService;
import models.ContentService;
import models.EventService;

public class ClubFeedPanel extends JPanel {
    private ContentService contentService;
    private EventService eventService;
    private ClubService clubService;
    private JPanel feedPanel;
    private JPanel eventPanel;
    private JComboBox<String> clubDropdown;
    private JTextField tagSearchField;
    private JButton searchButton;
    private JButton viewBookmarkedButton;
    private List<EventPanel> eventPanels;

    public ClubFeedPanel(ClubService clubService, EventService eventService) {
        this.clubService = clubService;
        this.eventService = eventService;
        this.contentService = new ContentService();
        this.eventPanels = new ArrayList<>();
        setLayout(new BorderLayout());

        contentService.initializeExampleContent();
        
        feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));

        eventPanel = new JPanel();
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
        eventPanel.setPreferredSize(new Dimension(eventPanel.getPreferredSize().width, 200)); // Set constant height

        JScrollPane feedScrollPane = new JScrollPane(feedPanel);
        JScrollPane eventScrollPane = new JScrollPane(eventPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, eventScrollPane, feedScrollPane);
        splitPane.setDividerLocation(400); // Adjusted to match the constant height

        add(splitPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        clubDropdown = new JComboBox<>();
        clubDropdown.addItem("All Clubs");
        for (Club club : clubService.getAllClubs()) {
            clubDropdown.addItem(club.getName());
        }
        clubDropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                filterEvents();
            }
        });

        tagSearchField = new JTextField(15);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterEvents();
            }
        });

        JButton uploadButton = new JButton("Upload Content");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UploadContentDialog(contentService, feedPanel);
            }
        });

        viewBookmarkedButton = new JButton("View Bookmarked");
        viewBookmarkedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBookmarkedEvents();
            }
        });

        controlPanel.add(new JLabel("Filter by Club:"));
        controlPanel.add(clubDropdown);
        controlPanel.add(new JLabel("Search by Tags:"));
        controlPanel.add(tagSearchField);
        controlPanel.add(searchButton);

        // Create a panel to place upload and view bookmarked buttons at the top right corner
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(uploadButton);
        buttonPanel.add(viewBookmarkedButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(controlPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        loadEvents();
        loadFeed();
    }

    private void loadFeed() {
        feedPanel.removeAll();
        for (Content content : contentService.getAllContent()) {
            feedPanel.add(new PostPanel(content));
        }
        feedPanel.revalidate();
        feedPanel.repaint();
    }

    private void loadEvents() {
        filterEvents();
    }

    private void filterEvents() {
        eventPanel.removeAll();
        eventPanels.clear();

        String selectedClub = (String) clubDropdown.getSelectedItem();
        String tagSearch = tagSearchField.getText().toLowerCase();

        List<Event> filteredEvents = eventService.getAllEvents().stream()
                .filter(event -> (selectedClub.equals("All Clubs") || event.getClub().getName().equals(selectedClub)) &&
                        (tagSearch.isEmpty() || event.getTags().stream().anyMatch(tag -> tag.getName().toLowerCase().contains(tagSearch))))
                .collect(Collectors.toList());

        for (Event event : filteredEvents) {
            EventPanel eventPanelItem = new EventPanel(event);
            eventPanels.add(eventPanelItem);
            eventPanel.add(eventPanelItem);
        }

        eventPanel.revalidate();
        eventPanel.repaint();
    }

    private void viewBookmarkedEvents() {
        JFrame bookmarkedFrame = new JFrame("Bookmarked Events");
        bookmarkedFrame.setSize(400, 400);
        bookmarkedFrame.setLocationRelativeTo(null);

        JPanel bookmarkedPanel = new JPanel();
        bookmarkedPanel.setLayout(new BoxLayout(bookmarkedPanel, BoxLayout.Y_AXIS));

        for (EventPanel eventPanelItem : eventPanels) {
            if (eventPanelItem.isBookmarked()) {
                bookmarkedPanel.add(eventPanelItem);
            }
        }

        JScrollPane scrollPane = new JScrollPane(bookmarkedPanel);
        bookmarkedFrame.add(scrollPane);

        bookmarkedFrame.setVisible(true);
    }
}
