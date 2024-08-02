package models; // Declare the package name.

import java.util.ArrayList; // Import ArrayList class.
import java.util.Date; // Import Date class.
import java.util.List; // Import List interface.

public class EventService { // Declare the public EventService class.
    private List<Event> eventList; // Private list to store events.
    private ClubService clubService; // Private ClubService instance.

    public EventService(ClubService clubService) { // Constructor to initialize event list and ClubService.
        this.eventList = new ArrayList<>(); // Initialize the event list.
        this.clubService = clubService; // Set the ClubService instance.

        // Adding sample events
        Club techClub = new Club("Tech Club", "A club for tech enthusiasts"); // Create Tech Club.
        Club sportsClub = new Club("Sports Club", "A club for sports lovers"); // Create Sports Club.

        clubService.addClub(techClub); // Add Tech Club to ClubService.
        clubService.addClub(sportsClub); // Add Sports Club to ClubService.

        Event event1 = new Event("Tech Talk", "A talk on the latest in tech", new Date(), techClub, "Assembly Hall"); // Create Event 1.
        event1.addTag(new ContentTag("technology")); // Add tag to Event 1.
        event1.addTag(new ContentTag("talk")); // Add tag to Event 1.

        Event event2 = new Event("Coding Workshop", "Learn to code in Java", new Date(), techClub, "Multi-Utility Room"); // Create Event 2.
        event2.addTag(new ContentTag("workshop")); // Add tag to Event 2.
        event2.addTag(new ContentTag("coding")); // Add tag to Event 2.

        Event event3 = new Event("Club Fair", "Explore various clubs", new Date(), sportsClub, "Main Ground"); // Create Event 3.
        event3.addTag(new ContentTag("fair")); // Add tag to Event 3.
        event3.addTag(new ContentTag("clubs")); // Add tag to Event 3.

        eventList.add(event1); // Add Event 1 to the list.
        eventList.add(event2); // Add Event 2 to the list.
        eventList.add(event3); // Add Event 3 to the list.
    }

    public void addEvent(Event event) { // Method to add an event.
        this.eventList.add(event); // Add the event to the list.
    }

    public List<Event> getAllEvents() { // Method to get all events.
        return eventList; // Return the list of events.
    }
}
