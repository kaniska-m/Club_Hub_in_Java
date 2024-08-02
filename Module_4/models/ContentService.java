package models; // Declare the package name.

import java.util.ArrayList; // Import ArrayList class.
import java.util.List; // Import List interface.

public class ContentService { // Declare the public ContentService class.
    private List<Content> contentList; // Private list to store content.

    public ContentService() { // Constructor to initialize the content list.
        this.contentList = new ArrayList<>();
    }

    public void addContent(Content content) { // Method to add content to the list.
        this.contentList.add(content);
    }

    public List<Content> getAllContent() { // Method to get all content.
        return contentList;
    }

    public void initializeExampleContent() { // Method to add example content.
        Content content1 = new Content("Introduction to Java", "Learn the basics of Java programming."); // Create content item 1.
        content1.addTag(new ContentTag("Java")); // Add tag to content item 1.
        content1.addTag(new ContentTag("Programming")); // Add tag to content item 1.

        Content content2 = new Content("Advanced Python", "Deep dive into advanced Python topics."); // Create content item 2.
        content2.addTag(new ContentTag("Python")); // Add tag to content item 2.
        content2.addTag(new ContentTag("Advanced")); // Add tag to content item 2.

        Content content3 = new Content("Web Development Basics", "Getting started with web development."); // Create content item 3.
        content3.addTag(new ContentTag("Web Development")); // Add tag to content item 3.
        content3.addTag(new ContentTag("HTML")); // Add tag to content item 3.
        content3.addTag(new ContentTag("CSS")); // Add tag to content item 3.

        this.contentList.add(content1); // Add content item 1 to the list.
        this.contentList.add(content2); // Add content item 2 to the list.
        this.contentList.add(content3); // Add content item 3 to the list.
    }
}
