import java.util.ArrayList;
import java.util.List;

public class Club {
    private String name;
    private String description;
    private String contactInfo;
    private String meetingSchedule;
    private String meetingLocation;
    private String onlineMeetingLink;
    private List<String> activities;
    private List<Member> members;
    private List<MembershipRequest> membershipRequests;

    public Club(String name, String description, String contactInfo, String meetingSchedule,
                String meetingLocation, String onlineMeetingLink, List<String> activities) {
        this.name = name;
        this.description = description;
        this.contactInfo = contactInfo;
        this.meetingSchedule = meetingSchedule;
        this.meetingLocation = meetingLocation;
        this.onlineMeetingLink = onlineMeetingLink;
        this.activities = activities;
        this.members = new ArrayList<>();
        this.membershipRequests = new ArrayList<>();
    }

    public List<Member> getApprovedMembers() {
        List<Member> approvedMembers = new ArrayList<>();
        for (Member member : members) {
            if (member.isApproved()) {
                approvedMembers.add(member);
            }
        }
        return approvedMembers;
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

    public List<Member> getMembers() {
        return members;
    }

    public List<MembershipRequest> getMembershipRequests() {
        return membershipRequests;
    }

    // Methods to add and remove members
    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    // Methods to manage membership requests
    public void addMembershipRequest(MembershipRequest request) {
        membershipRequests.add(request);
    }

    public void removeMembershipRequest(MembershipRequest request) {
        membershipRequests.remove(request);
    }

    @Override
    public String toString() {
        return name; // Assuming 'name' is the field storing the name of the club
    }
}
