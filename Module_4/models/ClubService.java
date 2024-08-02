package models; // Declare the package name.

import java.util.ArrayList; // Import ArrayList class.
import java.util.List; // Import List interface.

public class ClubService { // Declare the public ClubService class.
    private List<Club> clubList; // Private list to store clubs.

    public ClubService() { // Constructor to initialize the club list.
        this.clubList = new ArrayList<>();
    }

    public void addClub(Club club) { // Method to add a club to the list.
        this.clubList.add(club);
    }

    public List<Club> getAllClubs() { // Method to get all clubs.
        return clubList;
    }

    public Club getClubByName(String name) { // Method to find a club by name.
        for (Club club : clubList) { // Iterate through the list.
            if (club.getName().equals(name)) { // Check if the name matches.
                return club; // Return the matching club.
            }
        }
        return null; // Return null if no match is found.
    }
}
