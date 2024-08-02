public class MembershipRequest {
    private Member member;
    private String requestMessage;

    public MembershipRequest(Member member, String requestMessage) {
        this.member = member;
        this.requestMessage = requestMessage;
    }

    public Member getMember() {
        return member;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    @Override
    public String toString() {
        return member.toString() + " - " + requestMessage;
    }
}
