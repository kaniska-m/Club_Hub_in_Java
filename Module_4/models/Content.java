package models; // Declare the package name.

import java.util.ArrayList; // Import ArrayList class.
import java.util.List; // Import List interface.

public class Content { // Declare the public Content class.
    private String title; // Private instance variable for the title.
    private String description; // Private instance variable for the description.
    private List<ContentTag> tags; // Private list to store tags.

    public Content(String title, String description) { // Constructor to initialize title and description.
        this.title = title; // Set the title.
        this.description = description; // Set the description.
        this.tags = new ArrayList<>(); // Initialize the tags list.
    }

    public String getTitle() { // Getter for title.
        return title; // Return the title.
    }

    public String getDescription() { // Getter for description.
        return description; // Return the description.
    }

    public List<ContentTag> getTags() { // Getter for tags.
        return tags; // Return the list of tags.
    }

    public void addTag(ContentTag tag) { // Method to add a tag.
        this.tags.add(tag); // Add the tag to the list.
    }

    @Override
    public String toString() { // Override toString method.
        return title; // Return the title.
    }
}
