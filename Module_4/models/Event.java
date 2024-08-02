package models; // Declare the package name.

import java.util.ArrayList; // Import ArrayList class.
import java.util.Date; // Import Date class.
import java.util.List; // Import List interface.

public class Event { // Declare the public Event class.
    private String title; // Private instance variable for the event title.
    private String description; // Private instance variable for the event description.
    private Date date; // Private instance variable for the event date.
    private Club club; // Private instance variable for the associated club.
    private String location; // Private instance variable for the event location.
    private List<ContentTag> tags; // Private list to store tags.

    public Event(String title, String description, Date date, Club club, String location) { // Constructor to initialize event details.
        this.title = title; // Set the title.
        this.description = description; // Set the description.
        this.date = date; // Set the date.
        this.club = club; // Set the associated club.
        this.location = location; // Set the location.
        this.tags = new ArrayList<>(); // Initialize the tags list.
    }

    public String getTitle() { // Getter for title.
        return title; // Return the title.
    }

    public String getDescription() { // Getter for description.
        return description; // Return the description.
    }

    public Date getDate() { // Getter for date.
        return date; // Return the date.
    }

    public Club getClub() { // Getter for club.
        return club; // Return the associated club.
    }

    public String getLocation() { // Getter for location.
        return location; // Return the location.
    }

    public List<ContentTag> getTags() { // Getter for tags.
        return tags; // Return the list of tags.
    }

    public void addTag(ContentTag tag) { // Method to add a tag.
        this.tags.add(tag); // Add the tag to the list.
    }
}
