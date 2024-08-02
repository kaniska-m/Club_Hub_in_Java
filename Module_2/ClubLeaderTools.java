import java.util.List;

public class ClubLeaderTools {
    public Club club;

    public ClubLeaderTools(Club club) {
        this.club = club;
    }

    public void approveMembershipRequest(MembershipRequest request) {
        club.addMember(request.getMember());
        club.removeMembershipRequest(request);
    }

    public void declineMembershipRequest(MembershipRequest request) {
        club.removeMembershipRequest(request);
    }

    public List<Member> getMembers() {
        return club.getMembers();
    }

    public List<MembershipRequest> getMembershipRequests() {
        return club.getMembershipRequests();
    }
}
