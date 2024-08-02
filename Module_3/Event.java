import java.time.LocalDateTime;

public class Event {
    private String club;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private String link;
    private String posterUrl;

    public Event(String club, String title, String description, LocalDateTime dateTime, String location, String link, String posterUrl) {
        this.club = club;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.link = link;
        this.posterUrl = posterUrl;
    }

    // Getters and Setters
    public String getClub() { return club; }
    public void setClub(String club) { this.club = club; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }
}
