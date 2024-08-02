package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Content;
import models.ContentTag;
import models.ContentService;

public class UploadContentDialog extends JDialog {
    public UploadContentDialog(ContentService contentService, JPanel feedPanel) {
        setTitle("Upload Content");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        JTextField titleField = new JTextField();
        JTextArea descriptionArea = new JTextArea();
        JTextField tagsField = new JTextField();

        JButton uploadButton = new JButton("Upload");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionArea.getText();
                String[] tags = tagsField.getText().split(",");

                Content content = new Content(title, description); // Update constructor call
                for (String tag : tags) {
                    content.addTag(new ContentTag(tag.trim()));
                }

                contentService.addContent(content);
                feedPanel.add(new PostPanel(content));
                feedPanel.revalidate();
                feedPanel.repaint();
                dispose();
            }
        });

        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Description:"));
        add(new JScrollPane(descriptionArea));
        add(new JLabel("Tags (comma-separated):"));
        add(tagsField);
        add(new JLabel());
        add(uploadButton);

        setVisible(true);
    }
}
