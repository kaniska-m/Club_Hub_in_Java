import java.util.ArrayList;
import java.util.List;

public class ClubDiscoveryModule {
    public static void main(String[] args) {
        // Create a sample club directory
        ClubDirectory clubDirectory = new ClubDirectory();

        // Add sample clubs with activities, meeting location, and online meeting link
        List<String> chessActivities = new ArrayList<>();
        chessActivities.add("Chess Tournaments");
        chessActivities.add("Chess Workshops");
        clubDirectory.addClub(new Club("Chess Club", "Promotes chess among students",
                "chess@example.com", "Every Tuesday 3-5 PM", "Student Center Room 101",
                "https://meet.google.com/xyz-abc", chessActivities, "Games", "images/Chess club.png",
                "John Doe", "john@example.com", "123-456-7890", 
                "Jane Doe", "jane@example.com", "098-765-4321"));

        List<String> photoActivities = new ArrayList<>();
        photoActivities.add("Photography Excursions");
        photoActivities.add("Photo Editing Sessions");
        clubDirectory.addClub(new Club("Photography Club", "Explore the art of photography",
                "photo@example.com", "Every Friday 4-6 PM", "Art Building Room 205",
                null, photoActivities, "Photography", "images/Photography Club.png",
                "Alice Johnson", "alice@example.com", "234-567-8901", 
                "Bob White", "bob@example.com", "345-678-9012"));

        List<String> danceActivities = new ArrayList<>();
        danceActivities.add("Dance Practices");
        danceActivities.add("Performance Rehearsals");
        clubDirectory.addClub(new Club("ClassicaL Dance Club", "Learn and perform various dance forms",
                "dance@example.com", "Every Monday 6-8 PM", "Dance Studio A",
                "https://zoom.us/xyz", danceActivities, "Dance", "images/Classical Dance club.png",
                "Eve Smith", "eve@example.com", "456-789-0123", 
                "Charlie Brown", "charlie@example.com", "567-890-1234"));

        List<String> musicActivities = new ArrayList<>();
        musicActivities.add("Instrumental Practice");
        musicActivities.add("Band Performances");
        clubDirectory.addClub(new Club("Music Club", "Enjoy and create music",
                "music@example.com", "Every Thursday 5-7 PM", "Music Room 201",
                "https://meet.google.com/music", musicActivities, "Music", "images/Music Club.png",
                "Dan Black", "dan@example.com", "678-901-2345", 
                "Fiona Green", "fiona@example.com", "789-012-3456"));

        List<String> codingActivities = new ArrayList<>();
        codingActivities.add("Coding Bootcamps");
        codingActivities.add("Hackathons");
        clubDirectory.addClub(new Club("Backend Coding Club", "Improve coding skills through practice",
                "coding@example.com", "Every Saturday 1-3 PM", "Tech Building Room 101",
                "https://zoom.us/coding", codingActivities, "Coding", "images/Backend Coding Club.png",
                "George King", "george@example.com", "890-123-4567", 
                "Hannah Queen", "hannah@example.com", "901-234-5678"));

        List<String> codingActivities2 = List.of("Coding Bootcamps", "Hackathons");
        clubDirectory.addClub(new Club("Frontend Coding Club", "Improve coding skills through practice",
                "frontendcoding@example.com", "Every Saturday 4-6 PM", "Tech Building Room 102",
                "https://zoom.us/abc", codingActivities2, "Coding", "images/Frontend Coding Club.png",
                "Ivy Wright", "ivy@example.com", "012-345-6789", 
                "Jack Harris", "jack@example.com", "123-456-7890"));

        List<String> sportsActivities = new ArrayList<>();
        sportsActivities.add("Football Matches");
        sportsActivities.add("Basketball Games");
        clubDirectory.addClub(new Club("Sports Club", "Participate in various sports activities",
                "sports@example.com", "Every Sunday 10-12 AM", "Sports Ground",
                "https://meet.google.com/sports", sportsActivities, "Games", "images/Sports Club.png",
                "Kevin Turner", "kevin@example.com", "234-567-8901", 
                "Laura Wilson", "laura@example.com", "345-678-9012"));

        List<String> bookActivities = List.of("Book Discussions", "Author Meet and Greets");
        clubDirectory.addClub(new Club("Book Club", "Discuss and explore various books",
                "book@example.com", "Every Wednesday 5-7 PM", "Library Room 303",
                "https://meet.google.com/abc-def", bookActivities, "Literature", "images/Book Club.png",
                "Michael Clark", "michael@example.com", "456-789-0123", 
                "Nina Adams", "nina@example.com", "567-890-1234"));

        List<String> danceActivities2 = List.of("Ballet Practice", "Modern Dance Workshops");
        clubDirectory.addClub(new Club("Modern Dance Club", "Perform modern and classical dance forms",
                "moderndance@example.com", "Every Sunday 5-7 PM", "Dance Studio B",
                "https://meet.google.com/jkl-mno", danceActivities2, "Dance", "images/Modern Dance Club.png",
                "Olivia Baker", "olivia@example.com", "678-901-2345", 
                "Paul Davis", "paul@example.com", "789-012-3456"));

        // Launch the GUI
        ClubDiscoveryGUI clubDiscoveryGUI = new ClubDiscoveryGUI(clubDirectory);
        clubDiscoveryGUI.setVisible(true);
    }
}
