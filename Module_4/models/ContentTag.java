package models; // Declare the package name.

public class ContentTag { // Declare the public ContentTag class.
    private String name; // Private instance variable for the tag name.

    public ContentTag(String name) { // Constructor to initialize the tag name.
        this.name = name; // Set the name.
    }

    public String getName() { // Getter for name.
        return name; // Return the name.
    }

    @Override // Override toString method.
    public String toString() { // Define the string representation of the tag.
        return name; // Return the tag name.
    }
}
