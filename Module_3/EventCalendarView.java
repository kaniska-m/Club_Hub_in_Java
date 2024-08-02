import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class EventCalendarView extends JFrame {
    private EventManager eventManager;
    private JPanel calendarPanel;
    private EventDetailsPanel eventDetailsPanel;
    private JLabel calendarLabel;
    private JButton viewPersonalCalendarButton;

    public EventCalendarView(EventManager eventManager) {
        this.eventManager = eventManager;
        this.calendarPanel = new JPanel(new GridLayout(0, 7)); // 7 columns for days of the week
        this.eventDetailsPanel = new EventDetailsPanel(eventManager, false);
        this.calendarLabel = new JLabel("Event Calendar", JLabel.CENTER);
        this.viewPersonalCalendarButton = new JButton("View Personal Calendar");

        setTitle("Club Hub - Event Calendar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        calendarLabel.setFont(new Font("Serif", Font.BOLD, 24));
        calendarLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(createCalendarPanel());
        splitPane.setRightComponent(eventDetailsPanel);

        add(calendarLabel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(viewPersonalCalendarButton, BorderLayout.SOUTH);

        viewPersonalCalendarButton.addActionListener(e -> viewPersonalCalendar());

        populateSampleEvents();
        displayEvents();
    }

    private JPanel createCalendarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(calendarPanel), BorderLayout.CENTER);
        return panel;
    }

    private void displayEvents() {
        LocalDate today = LocalDate.now();
        int daysInMonth = today.lengthOfMonth();

        // Add day labels
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(new Font("Serif", Font.BOLD, 16));
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            calendarPanel.add(dayLabel);
        }

        // Fill the calendar with dates and events
        for (int i = 1; i <= daysInMonth; i++) {
            LocalDate currentDate = today.withDayOfMonth(i);
            JButton dayButton = new JButton(String.valueOf(i));
            dayButton.addActionListener(e -> eventDetailsPanel.displayEventsForDate(currentDate));
            dayButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            calendarPanel.add(dayButton);
        }

        revalidate();
        repaint();
    }

    private void populateSampleEvents() {
        Random random = new Random();
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 10; i++) {
            int day = random.nextInt(today.lengthOfMonth()) + 1;
            LocalDateTime eventDateTime = today.withDayOfMonth(day).atTime(random.nextInt(24), random.nextInt(60));
            Event event = new Event("Club " + (i + 1), "Event " + (i + 1), "Description " + (i + 1), eventDateTime, "Location " + (i + 1), "http://example.com", null);
            eventManager.addEvent(event);
        }
    }

    private void viewPersonalCalendar() {
        PersonalCalendarView personalCalendarView = new PersonalCalendarView(eventManager);
        personalCalendarView.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        EventCalendarView eventCalendarView = new EventCalendarView(eventManager);
        eventCalendarView.setVisible(true);
    }
}
