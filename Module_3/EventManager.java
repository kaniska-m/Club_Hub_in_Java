import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<Event> events;
    private List<Event> personalCalendar;

    public EventManager() {
        this.events = new ArrayList<>();
        this.personalCalendar = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public void addToPersonalCalendar(Event event) {
        personalCalendar.add(event);
    }

    public List<Event> getPersonalCalendarEvents() {
        return personalCalendar;
    }

    // Method to simulate sending push notifications
    public void sendPushNotification(Event event) {
        System.out.println("Push Notification: Upcoming Event - " + event.getTitle() + " at " + event.getDateTime());
    }
}
