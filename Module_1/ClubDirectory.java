import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// ClubDirectory class to manage the directory of clubs
class ClubDirectory {
    private List<Club> clubs;

    // Constructor
    public ClubDirectory() {
        this.clubs = new ArrayList<>();
    }

    // Method to add a new club to the directory
    public void addClub(Club club) {
        clubs.add(club);
    }

    // Method to display all clubs in the directory
    public void displayClubs() {
        for (Club club : clubs) {
            System.out.println("Name: " + club.getName());
            System.out.println("Description: " + club.getDescription());
            System.out.println("Contact Info: " + club.getContactInfo());
            System.out.println("Meeting Schedule: " + club.getMeetingSchedule());
            System.out.println("Meeting Location: " + club.getMeetingLocation());
            System.out.println("Online Meeting Link: " + club.getOnlineMeetingLink());
            System.out.println("Activities: " + club.getActivities());
            System.out.println("President: " + club.getPresidentName() + ", Email: " + club.getPresidentEmail() + ", Phone: " + club.getPresidentPhone());
            System.out.println("Vice President: " + club.getVicePresidentName() + ", Email: " + club.getVicePresidentEmail() + ", Phone: " + club.getVicePresidentPhone());
            System.out.println();
        }
    }

    // Method to get all clubs
    public List<Club> getAllClubs() {
        return new ArrayList<>(clubs);
    }

    // Method to get a club by name
    public Club getClubByName(String name) {
        for (Club club : clubs) {
            if (club.getName().equalsIgnoreCase(name)) {
                return club;
            }
        }
        return null;
    }

    // Method to search clubs by keyword in their name or description
    public List<Club> searchClubs(String keyword) {
        return clubs.stream()
                .filter(club -> club.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        club.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                        club.getActivities().stream().anyMatch(activity -> activity.toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    // Method to filter clubs by category
    public List<Club> filterClubsByCategory(String category) {
        return clubs.stream()
                .filter(club -> club.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
