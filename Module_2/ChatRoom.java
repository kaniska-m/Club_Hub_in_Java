import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private Club club;
    private List<Message> messages;

    public ChatRoom(Club club) {
        this.club = club;
        this.messages = new ArrayList<>();
    }

    public void postMessage(Member sender, String messageText) {
        Message message = new Message(sender, messageText);
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
