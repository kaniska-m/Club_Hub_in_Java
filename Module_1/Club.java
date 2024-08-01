import java.util.List;

// Club class to represent individual clubs
class Club {
    private String name;
    private String description;
    private String contactInfo;
    private String meetingSchedule;
    private String meetingLocation;
    private String onlineMeetingLink;
    private List<String> activities;
    private String category;
    private String imagePath; // Path to the club image
    private String presidentName;
    private String presidentEmail;
    private String presidentPhone;
    private String vicePresidentName;
    private String vicePresidentEmail;
    private String vicePresidentPhone;

    // Constructor
    public Club(String name, String description, String contactInfo, String meetingSchedule,
                String meetingLocation, String onlineMeetingLink, List<String> activities, String category, 
                String imagePath, String presidentName, String presidentEmail, String presidentPhone,
                String vicePresidentName, String vicePresidentEmail, String vicePresidentPhone) {
        this.name = name;
        this.description = description;
        this.contactInfo = contactInfo;
        this.meetingSchedule = meetingSchedule;
        this.meetingLocation = meetingLocation;
        this.onlineMeetingLink = onlineMeetingLink;
        this.activities = activities;
        this.category = category;
        this.imagePath = imagePath;
        this.presidentName = presidentName;
        this.presidentEmail = presidentEmail;
        this.presidentPhone = presidentPhone;
        this.vicePresidentName = vicePresidentName;
        this.vicePresidentEmail = vicePresidentEmail;
        this.vicePresidentPhone = vicePresidentPhone;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getMeetingSchedule() {
        return meetingSchedule;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public String getOnlineMeetingLink() {
        return onlineMeetingLink;
    }

    public List<String> getActivities() {
        return activities;
    }

    public String getCategory() {
        return category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public String getPresidentEmail() {
        return presidentEmail;
    }

    public String getPresidentPhone() {
        return presidentPhone;
    }

    public String getVicePresidentName() {
        return vicePresidentName;
    }

    public String getVicePresidentEmail() {
        return vicePresidentEmail;
    }

    public String getVicePresidentPhone() {
        return vicePresidentPhone;
    }
}
