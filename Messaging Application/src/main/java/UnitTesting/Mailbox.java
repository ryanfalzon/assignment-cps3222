package UnitTesting;

import java.util.ArrayList;
import java.util.List;

public class Mailbox {

    // Private properties
    private String agentID;
    private List<Message> messages;
    private int counter;

    // Getters and setters
    public String getAgentID() {
        return agentID;
    }
    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }
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
    public Mailbox(String agentID) {
        this.agentID = agentID;
        this.messages = new ArrayList<Message>();
        this.counter = 0;
    }

    // Returns the next SendMessage in the box on a FIFO bases
    public String consumeNextMessage() {

        // Return SendMessage if not empty
        if (counter < messages.size()) {
            counter++;

            // Check timestamp of current messgage
            if ((System.currentTimeMillis() - messages.get(counter - 1).getTimestamp()) <= 1800000){
                return messages.get(counter - 1).getMessage();
            }
            else{
                return consumeNextMessage();
            }
        }
        // Return null if empty
        else {
            return "Mailbox Empty";
        }
    }

    // Check if there are any messages in the mailbox
    public boolean hasMessages() {

        // Return true if there is at least one SendMessage
        if ((messages.size() > 0) && checkTimestamp()) {
            return true;
        }
        // Return false if empty
        else {
            return false;
        }
    }

    // Check timestamp of all messages in mailbox
    public boolean checkTimestamp(){

        // Iterate through all the messages
        for(int i = 0; i < messages.size(); i++){

            // Check current message timestamp
            if ((System.currentTimeMillis() - messages.get(i).getTimestamp()) > 1800000){
                return false;
            }
        }

        return true;
    }
}