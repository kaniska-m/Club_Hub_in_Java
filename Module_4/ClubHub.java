import GUI.MainFrame; // Import MainFrame class.
import models.*; // Import all classes from the models package.

public class ClubHub { // Declare the public ClubHub class.
    public static void main(String[] args) { // Main method to run the application.
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // Schedule a job for the event-dispatching thread.
            public void run() { // Run method to initialize services and GUI.
                ClubService clubService = new ClubService(); // Initialize ClubService.
                EventService eventService = new EventService(clubService); // Initialize EventService with ClubService.
                new MainFrame(clubService, eventService); // Create MainFrame with services.
            }
        });
    }
}
