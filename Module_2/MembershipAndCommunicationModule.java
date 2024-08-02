import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MembershipAndCommunicationModule extends JFrame {
    private List<Club> clubs;
    private JList<Club> clubList;
    private DefaultListModel<Club> clubListModel;
    private JTextArea clubInfoTextArea;
    private JTextArea messagesTextArea;
    private JTextArea forumTextArea;
    private JTextField nameField;
    private JTextField emailField;
    private JTextArea requestMessageArea;
    private JList<MembershipRequest> requestList;
    private DefaultListModel<MembershipRequest> requestListModel;
    private JList<Member> memberList;
    private DefaultListModel<Member> memberListModel;

    public MembershipAndCommunicationModule() {
        setTitle("Club Hub");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sample clubs
        clubs = new ArrayList<>();
        createSampleClubs();

        clubListModel = new DefaultListModel<>();
        clubList = new JList<>(clubListModel);
        clubList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clubList.addListSelectionListener(e -> showClubInfo());

        JScrollPane clubListScrollPane = new JScrollPane(clubList);

        JPanel clubPanel = new JPanel(new BorderLayout());
        clubPanel.add(new JLabel("Choose the Club"), BorderLayout.NORTH);
        clubPanel.add(clubListScrollPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(clubPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(3, 1));
        
        JButton requestMembershipButton = new JButton("Send Membership Request");
        requestMembershipButton.addActionListener(new RequestMembershipAction());
        controlPanel.add(requestMembershipButton);
        
        JButton memberButton = new JButton("I'm a Club Member");
        memberButton.addActionListener(new MemberAction());
        controlPanel.add(memberButton);

        JButton leaderButton = new JButton("I'm a Club Leader");
        leaderButton.addActionListener(new LeaderAction());
        controlPanel.add(leaderButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Initialize clubInfoTextArea
        clubInfoTextArea = new JTextArea();
        clubInfoTextArea.setEditable(false);
        populateClubList();
    }

    private void createSampleClubs() {
        List<String> chessActivities = new ArrayList<>();
        chessActivities.add("Chess Tournaments");
        chessActivities.add("Chess Workshops");
        Club chessClub = new Club("Chess Club", "Promotes chess among students", "chess@example.com",
                "Every Tuesday 3-5 PM", "Student Center Room 101", "https://meet.google.com/xyz-abc", chessActivities);
        clubs.add(chessClub);

        List<String> photoActivities = new ArrayList<>();
        photoActivities.add("Photography Excursions");
        photoActivities.add("Photo Editing Sessions");
        Club photoClub = new Club("Photography Club", "Explore the art of photography", "photo@example.com",
                "Every Friday 4-6 PM", "Art Building Room 205", null, photoActivities);
        clubs.add(photoClub);

        List<String> danceActivities = new ArrayList<>();
        danceActivities.add("Dance Practices");
        danceActivities.add("Performance Rehearsals");
        Club danceClub = new Club("Dance Club", "Learn and perform various dance forms", "dance@example.com",
                "Every Monday 6-8 PM", "Dance Studio A", "https://zoom.us/xyz", danceActivities);
        clubs.add(danceClub);
    }

    private void populateClubList() {
        for (Club club : clubs) {
            clubListModel.addElement(club);
        }
    }

    private void showClubInfo() {
        Club club = clubList.getSelectedValue();
        if (club != null) {
            clubInfoTextArea.setText("Name: " + club.getName() + "\n" +
                    "Description: " + club.getDescription() + "\n" +
                    "Contact Info: " + club.getContactInfo() + "\n" +
                    "Meeting Schedule: " + club.getMeetingSchedule() + "\n" +
                    "Meeting Location: " + club.getMeetingLocation() + "\n" +
                    "Online Meeting Link: " + club.getOnlineMeetingLink() + "\n" +
                    "Activities: " + String.join(", ", club.getActivities()));
        }
    }

    private class MemberAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Prompt user for name and email
            String name = JOptionPane.showInputDialog("Enter your name:");
            String email = JOptionPane.showInputDialog("Enter your email:");
    
            // Get the selected club
            Club club = clubList.getSelectedValue();
    
            if (club != null) {
                // Check if the user is an approved member of the club
                boolean isApprovedMember = false;
                for (Member member : club.getApprovedMembers()) {
                    if (member.getName().equals(name) && member.getEmail().equals(email)) {
                        isApprovedMember = true;
                        break;
                    }
                }
    
                // If the user is an approved member, show the member UI
                if (isApprovedMember) {
                    showMemberUI();
                } else {
                    JOptionPane.showMessageDialog(MembershipAndCommunicationModule.this, "You are not a member of this club. Apply for club membership to proceed.");
                }
            } else {
                JOptionPane.showMessageDialog(MembershipAndCommunicationModule.this, "Please select a club first.");
            }
        }
    }

    private class LeaderAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showLeaderUI();
        }
    }

    private class RequestMembershipAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (clubList.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(MembershipAndCommunicationModule.this, "Please select a club first.");
                return;
            }
            Club club = clubList.getSelectedValue();
            String name = JOptionPane.showInputDialog("Enter your name:");
            String email = JOptionPane.showInputDialog("Enter your email:");
            MembershipRequest request = new MembershipRequest(new Member(name, email), "Please accept my membership request.");
            club.addMembershipRequest(request);
            JOptionPane.showMessageDialog(MembershipAndCommunicationModule.this, "Membership request sent to the club leader.");
        }
    }

private void showMemberUI() {
    if (clubList.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(MembershipAndCommunicationModule.this, "Please select a club first.");
        return;
    }
    JFrame memberFrame = new JFrame("Club Member");
    memberFrame.setSize(800, 600);
    memberFrame.setLayout(new BorderLayout());

    JPanel infoPanel = new JPanel(new BorderLayout());
    clubInfoTextArea = new JTextArea();
    clubInfoTextArea.setEditable(false);
    infoPanel.add(new JLabel("Club Information"), BorderLayout.NORTH);
    infoPanel.add(new JScrollPane(clubInfoTextArea), BorderLayout.CENTER);

    // Display club information for the selected club
    Club club = clubList.getSelectedValue();
    clubInfoTextArea.setText("Name: " + club.getName() + "\n" +
            "Description: " + club.getDescription() + "\n" +
            "Contact Info: " + club.getContactInfo() + "\n" +
            "Meeting Schedule: " + club.getMeetingSchedule() + "\n" +
            "Meeting Location: " + club.getMeetingLocation() + "\n" +
            "Online Meeting Link: " + club.getOnlineMeetingLink() + "\n" +
            "Activities: " + String.join(", ", club.getActivities()));

    messagesTextArea = new JTextArea();
    messagesTextArea.setEditable(false);

    JPanel messagePanel = new JPanel(new BorderLayout());
    messagePanel.add(new JLabel("Messages"), BorderLayout.NORTH);
    messagePanel.add(new JScrollPane(messagesTextArea), BorderLayout.CENTER);

    forumTextArea = new JTextArea();
    forumTextArea.setEditable(false);

    JPanel forumPanel = new JPanel(new BorderLayout());
    forumPanel.add(new JLabel("Forum"), BorderLayout.NORTH);
    forumPanel.add(new JScrollPane(forumTextArea), BorderLayout.CENTER);

    JSplitPane infoMessageSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, infoPanel, messagePanel);
    infoMessageSplitPane.setResizeWeight(0.5);

    JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoMessageSplitPane, forumPanel);
    mainSplitPane.setResizeWeight(0.5);

    memberFrame.add(mainSplitPane, BorderLayout.CENTER);

    JPanel controlPanel = new JPanel(new GridLayout(5, 2));
    controlPanel.add(new JLabel("Name:"));
    nameField = new JTextField();
    controlPanel.add(nameField);
    controlPanel.add(new JLabel("Email:"));
    emailField = new JTextField();
    controlPanel.add(emailField);
    controlPanel.add(new JLabel("Message:"));
    requestMessageArea = new JTextArea();
    controlPanel.add(requestMessageArea);

    JButton sendMessageButton = new JButton("Send Message");
    sendMessageButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Club club = clubList.getSelectedValue();
            if (club != null) {
                String senderName = nameField.getText();
                String senderEmail = emailField.getText();
                String receiverName = JOptionPane.showInputDialog(memberFrame, "Enter receiver's name:");
                String receiverEmail = JOptionPane.showInputDialog(memberFrame, "Enter receiver's email:");
                String message = requestMessageArea.getText();
                Member sender = new Member(senderName, senderEmail);
                Member receiver = new Member(receiverName, receiverEmail);
                String datetime = LocalDateTime.now().toString();
                String formattedMessage = String.format("[%s] From: %s <%s>, To: %s <%s>\n%s\n\n",
                        datetime, sender.getName(), sender.getEmail(), receiver.getName(), receiver.getEmail(), message);
                JOptionPane.showMessageDialog(MembershipAndCommunicationModule.this, "Message sent.");
                messagesTextArea.append(formattedMessage);
            }
        }
    });
    controlPanel.add(sendMessageButton);

    JButton postMessageButton = new JButton("Post Message in Forum");
    postMessageButton.addActionListener(new PostMessageAction());
    controlPanel.add(postMessageButton);

    memberFrame.add(controlPanel, BorderLayout.SOUTH);

    memberFrame.setVisible(true);
}

private void showLeaderUI() {
    if (clubList.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(MembershipAndCommunicationModule.this, "Please select a club first.");
        return;
    }
    JFrame leaderFrame = new JFrame("Club Leader");
    leaderFrame.setSize(800, 600);
    leaderFrame.setLayout(new BorderLayout());

    JPanel infoPanel = new JPanel(new BorderLayout());
    clubInfoTextArea = new JTextArea();
    clubInfoTextArea.setEditable(false);
    infoPanel.add(new JLabel("Club Information"), BorderLayout.NORTH);
    infoPanel.add(new JScrollPane(clubInfoTextArea), BorderLayout.CENTER);

    // Display club information for the selected club
    Club club = clubList.getSelectedValue();
    clubInfoTextArea.setText("Name: " + club.getName() + "\n" +
            "Description: " + club.getDescription() + "\n" +
            "Contact Info: " + club.getContactInfo() + "\n" +
            "Meeting Schedule: " + club.getMeetingSchedule() + "\n" +
            "Meeting Location: " + club.getMeetingLocation() + "\n" +
            "Online Meeting Link: " + club.getOnlineMeetingLink() + "\n" +
            "Activities: " + String.join(", ", club.getActivities()));

    messagesTextArea = new JTextArea();
    messagesTextArea.setEditable(false);

    JPanel messagePanel = new JPanel(new BorderLayout());
    messagePanel.add(new JLabel("Messages"), BorderLayout.NORTH);
    messagePanel.add(new JScrollPane(messagesTextArea), BorderLayout.CENTER);

    forumTextArea = new JTextArea();
    forumTextArea.setEditable(false);

    JPanel forumPanel = new JPanel(new BorderLayout());
    forumPanel.add(new JLabel("Forum"), BorderLayout.NORTH);
    forumPanel.add(new JScrollPane(forumTextArea), BorderLayout.CENTER);

    JSplitPane infoMessageSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, infoPanel, messagePanel);
    infoMessageSplitPane.setResizeWeight(0.5);

    JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoMessageSplitPane, forumPanel);
    mainSplitPane.setResizeWeight(0.5);

    leaderFrame.add(mainSplitPane, BorderLayout.CENTER);

    JPanel controlPanel = new JPanel(new GridLayout(6, 2));
    controlPanel.add(new JLabel("Name:"));
    nameField = new JTextField();
    controlPanel.add(nameField);
    controlPanel.add(new JLabel("Email:"));
    emailField = new JTextField();
    controlPanel.add(emailField);
    controlPanel.add(new JLabel("Message:"));
    requestMessageArea = new JTextArea();
    controlPanel.add(requestMessageArea);

    JButton viewRequestsButton = new JButton("View Requests");
    viewRequestsButton.addActionListener(new ViewRequestsAction());
    controlPanel.add(viewRequestsButton);

    JButton sendMessageButton = new JButton("Send Message");
    sendMessageButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Club club = clubList.getSelectedValue();
            if (club != null) {
                String senderName = nameField.getText();
                String senderEmail = emailField.getText();
                String receiverName = JOptionPane.showInputDialog(leaderFrame, "Enter receiver's name:");
                String receiverEmail = JOptionPane.showInputDialog(leaderFrame, "Enter receiver's email:");
                String message = requestMessageArea.getText();
                Member sender = new Member(senderName, senderEmail);
                Member receiver = new Member(receiverName, receiverEmail);
                String datetime = LocalDateTime.now().toString();
                String formattedMessage = String.format("[%s] From: %s <%s>, To: %s <%s>\n%s\n\n",
                        datetime, sender.getName(), sender.getEmail(), receiver.getName(), receiver.getEmail(), message);
                JOptionPane.showMessageDialog(MembershipAndCommunicationModule.this, "Message sent.");
                messagesTextArea.append(formattedMessage);
            }
        }
    });
    controlPanel.add(sendMessageButton);

    JButton postMessageButton = new JButton("Post Message in Forum");
    postMessageButton.addActionListener(new PostMessageAction());
    controlPanel.add(postMessageButton);

    JButton membersDirectoryButton = new JButton("Members Directory");
    membersDirectoryButton.addActionListener(new MembersDirectoryAction());
    controlPanel.add(membersDirectoryButton);

    leaderFrame.add(controlPanel, BorderLayout.SOUTH);

    leaderFrame.setVisible(true);
}

    private class PostMessageAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Club club = clubList.getSelectedValue();
            if (club != null) {
                String name = nameField.getText();
                String email = emailField.getText();
                String message = requestMessageArea.getText();
                Member sender = new Member(name, email);

                ChatRoom chatRoom = new ChatRoom(club);
                chatRoom.postMessage(sender, message);
                JOptionPane.showMessageDialog(MembershipAndCommunicationModule.this, "Message posted in forum.");
                forumTextArea.append(sender.getName() + ": " + message + "\n");
            }
        }
    }

    private class ViewRequestsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Club club = clubList.getSelectedValue();
            if (club != null) {
                requestListModel = new DefaultListModel<>();
                requestList = new JList<>(requestListModel);
                for (MembershipRequest request : club.getMembershipRequests()) {
                    requestListModel.addElement(request);
                }

                JFrame requestFrame = new JFrame("Membership Requests");
                requestFrame.setSize(400, 300);
                requestFrame.setLayout(new BorderLayout());

                JPanel requestPanel = new JPanel(new BorderLayout());
                requestPanel.add(new JLabel("Requests"), BorderLayout.NORTH);
                requestPanel.add(new JScrollPane(requestList), BorderLayout.CENTER);

                JButton approveButton = new JButton("Approve");
                approveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MembershipRequest selectedRequest = requestList.getSelectedValue();
                        if (selectedRequest != null) {
                            ClubLeaderTools tools = new ClubLeaderTools(club);
                            tools.approveMembershipRequest(selectedRequest);
                            requestListModel.removeElement(selectedRequest);
                            Member selectedMember = selectedRequest.getMember();
                            selectedMember.isApproved(true);
                            JOptionPane.showMessageDialog(requestFrame, "Membership request approved.");
                        }
                    }
                });

                JButton declineButton = new JButton("Decline");
                declineButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MembershipRequest selectedRequest = requestList.getSelectedValue();
                        if (selectedRequest != null) {
                            ClubLeaderTools tools = new ClubLeaderTools(club);
                            tools.declineMembershipRequest(selectedRequest);
                            requestListModel.removeElement(selectedRequest);
                            JOptionPane.showMessageDialog(requestFrame, "Membership request declined.");
                        }
                    }
                });

                JPanel buttonPanel = new JPanel();
                buttonPanel.add(approveButton);
                buttonPanel.add(declineButton);

                requestFrame.add(requestPanel, BorderLayout.CENTER);
                requestFrame.add(buttonPanel, BorderLayout.SOUTH);

                requestFrame.setVisible(true);
            }
        }
    }

    private class MembersDirectoryAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Club club = clubList.getSelectedValue();
            if (club != null) {
                JFrame membersDirectoryFrame = new JFrame("Members Directory");
                membersDirectoryFrame.setSize(400, 300);
                membersDirectoryFrame.setLayout(new BorderLayout());
    
                memberListModel = new DefaultListModel<>();
                memberList = new JList<>(memberListModel);
    
                for (Member member : club.getApprovedMembers()) {
                    memberListModel.addElement(member);
                }
    
                JPanel memberPanel = new JPanel(new BorderLayout());
                memberPanel.add(new JLabel("Club Members"), BorderLayout.NORTH);
                memberPanel.add(new JScrollPane(memberList), BorderLayout.CENTER);
    
                JButton removeMemberButton = new JButton("Remove Member");
                removeMemberButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Member selectedMember = memberList.getSelectedValue();
                        if (selectedMember != null) {
                            club.removeMember(selectedMember);
                            memberListModel.removeElement(selectedMember);
                            JOptionPane.showMessageDialog(membersDirectoryFrame, "Member removed from the club.");
                        }
                    }
                });
    
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(removeMemberButton);
    
                membersDirectoryFrame.add(memberPanel, BorderLayout.CENTER);
                membersDirectoryFrame.add(buttonPanel, BorderLayout.SOUTH);
    
                membersDirectoryFrame.setVisible(true);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MembershipAndCommunicationModule ui = new MembershipAndCommunicationModule();
            ui.setVisible(true);
        });
    }
}
