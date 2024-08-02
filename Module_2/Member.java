public class Member {
    private String name;
    private String email;
    private boolean approved;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.approved = false;
    }

    public String getName() {
        return name;
    }

    public boolean isApproved() {
        return approved;
    }
    
    public void isApproved(boolean approved) {
        this.approved = approved;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
