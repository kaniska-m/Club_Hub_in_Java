import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PersonalCalendarView extends JFrame {
    private EventManager eventManager;
    private JPanel calendarPanel;
    private EventDetailsPanel eventDetailsPanel;
    private JLabel calendarLabel;
    private JButton viewEventCalendarButton;

    public PersonalCalendarView(EventManager eventManager) {
        this.eventManager = eventManager;
        this.calendarPanel = new JPanel(new GridLayout(0, 7)); // 7 columns for days of the week
        this.eventDetailsPanel = new EventDetailsPanel(eventManager, true); // Pass true to indicate personal calendar
        this.calendarLabel = new JLabel("Personal Calendar", JLabel.CENTER);
        this.viewEventCalendarButton = new JButton("View Event Calendar");

        setTitle("Club Hub - Personal Calendar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        calendarLabel.setFont(new Font("Serif", Font.BOLD, 24));
        calendarLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(calendarLabel, BorderLayout.NORTH);
        headerPanel.add(createMonthYearLabel(), BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(createCalendarPanel());
        splitPane.setRightComponent(eventDetailsPanel);

        add(headerPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(viewEventCalendarButton, BorderLayout.SOUTH);

        viewEventCalendarButton.addActionListener(e -> viewEventCalendar());

        displayPersonalCalendar();
    }

    private JPanel createCalendarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(calendarPanel), BorderLayout.CENTER);
        return panel;
    }

    private JLabel createMonthYearLabel() {
        LocalDate today = LocalDate.now();
        String monthYear = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + today.getYear();
        JLabel monthYearLabel = new JLabel(monthYear, JLabel.CENTER);
        monthYearLabel.setFont(new Font("Serif", Font.BOLD, 20));
        return monthYearLabel;
    }

    private void displayPersonalCalendar() {
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

        // Fill the calendar with dates and personal events
        for (int i = 1; i <= daysInMonth; i++) {
            LocalDate currentDate = today.withDayOfMonth(i);
            JButton dayButton = new JButton(String.valueOf(i));
            dayButton.addActionListener(e -> displayEventsForDate(currentDate));
            dayButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            calendarPanel.add(dayButton);
        }

        revalidate();
        repaint();
    }

    private void displayEventsForDate(LocalDate date) {
        List<Event> events = eventManager.getPersonalCalendarEvents().stream()
                .filter(event -> event.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());

        eventDetailsPanel.displayEventDetails(events);
    }

    private void viewEventCalendar() {
        EventCalendarView eventCalendarView = new EventCalendarView(eventManager);
        eventCalendarView.setVisible(true);
        this.dispose();
    }
}
