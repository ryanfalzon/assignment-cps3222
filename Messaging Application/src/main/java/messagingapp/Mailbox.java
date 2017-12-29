package messagingapp;

import java.util.ArrayList;
import java.util.List;

public class Mailbox {

    // Private properties
    private List<Message> messages;
    private int counter;

    // Getters and setters
    public List<Message> getMessages() {
        return this.messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public int getCounter() {
        return this.counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }

    // Default constructor
    public Mailbox() {
        this.messages = new ArrayList<Message>();
        counter = 0;
    }

    // Returns the next message in the box on a FIFO bases
    public String consumeNextMessage() {

        // Return message if not empty
        if (counter < messages.size()) {
            return messages.get(counter).getMessage();
        }
        // Return null if empty
        else {
            return "Mailbox Empty";
        }
    }

    // Check if there are any messages in the mailbox
    public boolean hasMessages() {

        // Return true if there is at least one message
        if (messages.size() > 0) {
            return true;
        }
        // Return false if empty
        else {
            return false;
        }
    }
}
