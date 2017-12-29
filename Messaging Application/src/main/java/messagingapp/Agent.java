package messagingapp;

public class Agent {

    // Private properties
    private String id;
    private String name;
    private String sessionId;
    private Mailbox mailbox;
    private int sentCount;
    private int receivecCount;
    private Supervisor supervisor;

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
    public int getReceivecCount() {
        return receivecCount;
    }
    public void setReceivecCount(int receivecCount) {
        this.receivecCount = receivecCount;
    }
    public int getSentCount() {
        return sentCount;
    }
    public void setSentCount(int sentCount) {
        this.sentCount = sentCount;
    }

    // Constructor
    public Agent(String id, String name){
        this.id = id;
        this.name = name;
        this.sessionId = "";
        receivecCount = 0;
        sentCount = 0;
        mailbox = new Mailbox();
    }

    // Initiate contact with a supervisor to get a login key and subsequently logs into the system
    public LoginKey login(){

        // Contact the supervisor
        LoginKey loginKey = supervisor.getLoginKey(this);
        return loginKey;
    }

    // Sends a message to the destination agent
    public boolean sendMessage(Agent destinationAgent, Message message)
    {
        // Check how many messages the sender has sent
        if(sentCount < 25){

            // Check how many messages the receiver has sent
            if(destinationAgent.getReceivecCount() < 25){
                destinationAgent.getMailbox().getMessages().add(message);
                return true;
            }
            else{
                destinationAgent.setSessionId(null);
                return false;
            }
        }
        else{
            this.setSessionId(null);
            return false;
        }
    }
}