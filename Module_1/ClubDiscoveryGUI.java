import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClubDiscoveryGUI extends JFrame {
    private ClubDirectory clubDirectory;
    private JList<String> clubList;
    private DefaultListModel<String> clubListModel;
    private JPanel detailsPanel;
    private JLabel clubImageLabel;
    private JTextField searchField;
    private JComboBox<String> filterComboBox;

    public ClubDiscoveryGUI(ClubDirectory clubDirectory) {
        this.clubDirectory = clubDirectory;
        setTitle("Club Discovery Module");
        setSize(900, 600); // Adjusted the frame size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Left panel with club list, search, and filter
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight())); // Set width to one-third of the screen

        // Club list
        clubListModel = new DefaultListModel<>();
        clubList = new JList<>(clubListModel);
        clubList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clubList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane listScrollPane = new JScrollPane(clubList);

        // Search field
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setIcon(new ImageIcon("icons/search.png")); // Add search icon
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchClubs();
            }
        });

        // Filter combo box
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        filterComboBox = new JComboBox<>();
        loadCategories();
        JButton filterButton = new JButton("Filter");
        filterButton.setPreferredSize(new Dimension(100, 30));
        filterButton.setIcon(new ImageIcon("icons/filter.png")); // Add filter icon
        filterPanel.add(filterComboBox);
        filterPanel.add(filterButton);

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterClubs();
            }
        });

        leftPanel.add(searchPanel, BorderLayout.NORTH);
        leftPanel.add(listScrollPane, BorderLayout.CENTER);
        leftPanel.add(filterPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);

        // Right panel with club details
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setPreferredSize(new Dimension(getWidth() * 2 / 3, getHeight())); // Set width to two-thirds of the screen
        JScrollPane detailsScrollPane = new JScrollPane(detailsPanel);
        add(detailsScrollPane, BorderLayout.CENTER);

        // Club image
        clubImageLabel = new JLabel();
        clubImageLabel.setHorizontalAlignment(JLabel.CENTER);
        clubImageLabel.setPreferredSize(new Dimension(200, 200));
        detailsPanel.add(clubImageLabel);

        // Club list selection listener
        clubList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedClub = clubList.getSelectedValue();
                    displayClubDetails(selectedClub);
                }
            }
        });

        loadClubs();
    }

    private void loadClubs() {
        clubListModel.clear();
        List<Club> clubs = clubDirectory.getAllClubs();
        for (Club club : clubs) {
            clubListModel.addElement(club.getName());
        }
        if (clubListModel.isEmpty()) {
            clubListModel.addElement("No clubs found");
        }
    }

    private void displayClubDetails(String clubName) {
        detailsPanel.removeAll();
        if (clubName != null && !clubName.equals("No clubs found")) {
            Club club = clubDirectory.getClubByName(clubName);
            if (club != null) {
                // Create a panel to hold the club details
                JPanel clubDetails = new JPanel();
                clubDetails.setLayout(new BoxLayout(clubDetails, BoxLayout.Y_AXIS));
                clubDetails.setBorder(BorderFactory.createTitledBorder("Club Details"));

                JLabel nameLabel = new JLabel("Name: " + club.getName());
                nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
                JLabel descriptionLabel = new JLabel("<html>Description: " + club.getDescription() + "</html>");
                descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel contactLabel = new JLabel("Contact Info: " + club.getContactInfo());
                contactLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel scheduleLabel = new JLabel("Meeting Schedule: " + club.getMeetingSchedule());
                scheduleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel locationLabel = new JLabel("Meeting Location: " + club.getMeetingLocation());
                locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel onlineMeetingLabel = new JLabel("Online Meeting Link: " + club.getOnlineMeetingLink());
                onlineMeetingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel activitiesLabel = new JLabel("Activities: " + String.join(", ", club.getActivities()));
                activitiesLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                clubDetails.add(nameLabel);
                clubDetails.add(descriptionLabel);
                clubDetails.add(contactLabel);
                clubDetails.add(scheduleLabel);
                clubDetails.add(locationLabel);
                clubDetails.add(onlineMeetingLabel);
                clubDetails.add(activitiesLabel);
// Ensure that the JLabel is properly initialized somewhere in your code
JLabel clubImageLabel = new JLabel();
clubImageLabel.setHorizontalAlignment(JLabel.CENTER); // Center horizontally
clubImageLabel.setVerticalAlignment(JLabel.CENTER);   // Center vertically
detailsPanel.add(clubImageLabel); // Add the label to the panel if not already added

// Display club image
if (club.getImagePath() != null && !club.getImagePath().isEmpty()) {
    File imgFile = new File(club.getImagePath());
    System.out.println("Image path: " + club.getImagePath()); // Debug statement
    System.out.println("Image file exists: " + imgFile.exists()); // Debug statement
    if (imgFile.exists()) {
        try {
            // Load the image
            ImageIcon clubImage = new ImageIcon(new ImageIcon(club.getImagePath()).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
            clubImageLabel.setIcon(clubImage);
            clubImageLabel.setText(null); // Clear any text
            System.out.println("Image successfully loaded and set."); // Debug statement
        } catch (Exception e) {
            e.printStackTrace();
            clubImageLabel.setIcon(null);
            clubImageLabel.setText("Error loading image");
        }
    } else {
        clubImageLabel.setIcon(null);
        clubImageLabel.setText("Image not found");
    }
} else {
    clubImageLabel.setIcon(null);
    clubImageLabel.setText("No image available");
}

// Ensure the panel is revalidated and repainted
detailsPanel.revalidate();
detailsPanel.repaint();



                // Create a panel to hold the president and vice president details
                JPanel contactDetails = new JPanel();
                contactDetails.setLayout(new BoxLayout(contactDetails, BoxLayout.Y_AXIS));
                contactDetails.setBorder(BorderFactory.createTitledBorder("Contact Information"));

                JLabel presidentLabel = new JLabel("President: " + club.getPresidentName());
                presidentLabel.setFont(new Font("Arial", Font.BOLD, 14));
                JLabel presidentEmailLabel = new JLabel("Email: " + club.getPresidentEmail());
                presidentEmailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel presidentPhoneLabel = new JLabel("Phone: " + club.getPresidentPhone());
                presidentPhoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                JLabel vicePresidentLabel = new JLabel("Vice President: " + club.getVicePresidentName());
                vicePresidentLabel.setFont(new Font("Arial", Font.BOLD, 14));
                JLabel vicePresidentEmailLabel = new JLabel("Email: " + club.getVicePresidentEmail());
                vicePresidentEmailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel vicePresidentPhoneLabel = new JLabel("Phone: " + club.getVicePresidentPhone());
                vicePresidentPhoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                contactDetails.add(presidentLabel);
                contactDetails.add(presidentEmailLabel);
                contactDetails.add(presidentPhoneLabel);
                contactDetails.add(vicePresidentLabel);
                contactDetails.add(vicePresidentEmailLabel);
                contactDetails.add(vicePresidentPhoneLabel);

                detailsPanel.add(clubDetails);
                detailsPanel.add(contactDetails);

                // Contact button
                JButton contactButton = new JButton("Contact Club Authorities");
                contactButton.setFont(new Font("Arial", Font.BOLD, 14));
                contactButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int confirmation = JOptionPane.showConfirmDialog(
                                ClubDiscoveryGUI.this,
                                "Do you want to contact the club authorities?",
                                "Contact Confirmation",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (confirmation == JOptionPane.YES_OPTION) {
                            // Code to contact club authorities (e.g., send an email)
                            JOptionPane.showMessageDialog(ClubDiscoveryGUI.this, "Club authorities have been contacted.");
                        }
                    }
                });
                detailsPanel.add(contactButton);
            }
        } else {
            detailsPanel.add(new JLabel("No clubs found"));
        }
        revalidate();
        repaint();
    }

    private void searchClubs() {
        String keyword = searchField.getText().trim();
        clubListModel.clear();
        List<Club> searchResults = clubDirectory.searchClubs(keyword);
        for (Club club : searchResults) {
            clubListModel.addElement(club.getName());
        }
        if (clubListModel.isEmpty()) {
            clubListModel.addElement("No clubs found");
        }
    }

    private void filterClubs() {
        String category = (String) filterComboBox.getSelectedItem();
        clubListModel.clear();
        List<Club> filterResults = clubDirectory.filterClubsByCategory(category);
        for (Club club : filterResults) {
            clubListModel.addElement(club.getName());
        }
        if (clubListModel.isEmpty()) {
            clubListModel.addElement("No clubs found");
        }
    }

    private void loadCategories() {
        Set<String> categories = new HashSet<>();
        for (Club club : clubDirectory.getAllClubs()) {
            categories.add(club.getCategory());
        }
        filterComboBox.addItem("All");
        for (String category : categories) {
            filterComboBox.addItem(category);
        }
    }
}