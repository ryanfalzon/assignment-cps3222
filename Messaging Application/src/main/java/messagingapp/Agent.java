package messagingapp;

public class Agent {

    // Private properties
    private String id;
    private String name;
    private String sessionId;
    private Mailbox mailbox;
    private int sendMessagesCount;

    // Getters and setters
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(){
        this.name = name;
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public Mailbox getMailbox() {
        return this.mailbox;
    }
    public void setMailbox(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    // Default constructor
    public Agent(){
        sendMessagesCount = 0;
    }

    // Constructor
    public Agent(String id, String name){
        this.id = id;
        this.name = name;
        sendMessagesCount = 0;
    }

    // Initiate contact with a supervisor to get a login key and subsequently logs into the system
    public boolean login(Supervisor supervisor, MessagingSystem system){

        LoginKey loginKey = supervisor.getLoginKey(this);

        // Return true if successful
        if(system.login(this, loginKey) != null){
            return true;
        }
        // Return false otherwise
        else{
            return false;
        }
    }

    // Sends a message to the destination agent
    public boolean sendMessage(Agent destinationAgent, String message, MessagingSystem system){

        if(sendMessagesCount < 25){

            // Return true if successful
            if((system.sendMessage(this, destinationAgent, message) != null)){
                return true;
            }
            // Return false otherwise
            else{
                return false;
            }
        }
        else{
            this.sessionId = null;
            return false;
        }
    }
}
