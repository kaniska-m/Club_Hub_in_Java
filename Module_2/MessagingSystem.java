import java.util.ArrayList;
import java.util.List;

public class MessagingSystem {
    private Club club;
    private List<Message> messages;

    public MessagingSystem(Club club) {
        this.club = club;
        this.messages = new ArrayList<>();
    }

    public void sendMessage(Member sender, String messageText) {
        Message message = new Message(sender, messageText);
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}

class Message {
    private Member sender;
    private String text;

    public Message(Member sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public Member getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return sender.getName() + ": " + text;
    }
}
