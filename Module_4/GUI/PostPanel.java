package GUI;

import javax.swing.*;
import java.awt.*;
import models.Content;

public class PostPanel extends JPanel {
    public PostPanel(Content content) {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel postsHeading = new JLabel("Post");
        postsHeading.setHorizontalAlignment(SwingConstants.CENTER);
        postsHeading.setFont(new Font("Serif", Font.BOLD, 18));
        postsHeading.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel titleLabel = new JLabel(content.getTitle());
        JLabel tagLabel = new JLabel(content.getTags().toString());
        JTextArea contentArea = new JTextArea(content.getDescription());
        contentArea.setEditable(false);

        add(postsHeading, BorderLayout.NORTH);
        add(titleLabel, BorderLayout.WEST);
        add(contentArea, BorderLayout.CENTER);
        add(tagLabel, BorderLayout.SOUTH);
    }
}
