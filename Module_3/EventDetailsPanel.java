import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class EventDetailsPanel extends JPanel {
    private EventManager eventManager;
    private JPanel eventDetailsArea;
    private JTextField searchField;
    private boolean isPersonalCalendar;

    public EventDetailsPanel(EventManager eventManager, boolean isPersonalCalendar) {
        this.eventManager = eventManager;
        this.isPersonalCalendar = isPersonalCalendar;
        this.eventDetailsArea = new JPanel();
        this.searchField = new JTextField(20);

        setLayout(new BorderLayout());

        JLabel searchLabel = new JLabel("Search Events: ");
        searchLabel.setFont(new Font("Serif", Font.BOLD, 16));
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchField.addActionListener(e -> searchEvents());

        add(searchPanel, BorderLayout.NORTH);
        eventDetailsArea.setLayout(new BoxLayout(eventDetailsArea, BoxLayout.Y_AXIS));
        add(new JScrollPane(eventDetailsArea), BorderLayout.CENTER);

    }

    public void displayEventsForDate(LocalDate date) {
        List<Event> events = (isPersonalCalendar ? eventManager.getPersonalCalendarEvents() : eventManager.getAllEvents()).stream()
                .filter(event -> event.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());

        displayEventDetails(events);
    }

    private void searchEvents() {
        String searchTerm = searchField.getText().toLowerCase();
        List<Event> filteredEvents = (isPersonalCalendar ? eventManager.getPersonalCalendarEvents() : eventManager.getAllEvents()).stream()
                .filter(event -> event.getTitle().toLowerCase().contains(searchTerm) ||
                        event.getDescription().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

        displayEventDetails(filteredEvents);
    }

    public void displayEventDetails(List<Event> events) {
        eventDetailsArea.removeAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Event event : events) {
            JPanel eventInfoPanel = new JPanel();
            eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
            eventInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel clubLabel = new JLabel("Club: " + event.getClub());
            clubLabel.setFont(new Font("Serif", Font.BOLD, 14));
            JLabel titleLabel = new JLabel("Title: " + event.getTitle());
            JLabel descriptionLabel = new JLabel("Description: " + event.getDescription());
            JLabel dateTimeLabel = new JLabel("Date/Time: " + event.getDateTime().format(formatter));
            JLabel locationLabel = new JLabel("Location: " + event.getLocation());
            JLabel linkLabel = new JLabel("Link: " + event.getLink());

            eventInfoPanel.add(clubLabel);
            eventInfoPanel.add(titleLabel);
            eventInfoPanel.add(descriptionLabel);
            eventInfoPanel.add(dateTimeLabel);
            eventInfoPanel.add(locationLabel);
            eventInfoPanel.add(linkLabel);

            if (event.getPosterUrl() != null && !event.getPosterUrl().isEmpty()) {
                ImageIcon posterIcon = new ImageIcon(event.getPosterUrl());
                JLabel posterLabel = new JLabel(posterIcon);
                eventInfoPanel.add(posterLabel);
            }

            if (!isPersonalCalendar) { 
                JButton addToCalendarButton = new JButton("Add to Personal Calendar");
                                addToCalendarButton.addActionListener(e -> {
                                    eventManager.addToPersonalCalendar(event);
                                    JOptionPane.showMessageDialog(this, "Event '" + event.getTitle() + "' added to your personal calendar.");
                                });
                                eventInfoPanel.add(addToCalendarButton);
                            }

            eventDetailsArea.add(eventInfoPanel);
            eventDetailsArea.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        revalidate();
        repaint();
    }
}