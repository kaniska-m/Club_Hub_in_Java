package models; // Declare the package name.

public class Club { // Declare the public Club class.

    private String name; // Private instance variable for the club's name.
    private String description; // Private instance variable for the club's description.

    public Club(String name, String description) { // Constructor to initialize name and description.
        this.name = name; // Set the name.
        this.description = description; // Set the description.
    }

    public String getName() { // Getter for name.
        return name; // Return the name.
    }

    public String getDescription() { // Getter for description.
        return description; // Return the description.
    }

    @Override // Override the toString method.
    public String toString() { // Define the string representation of the Club.
        return name; // Return the club's name.
    }
}
