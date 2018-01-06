package backend;

import servlets.StaticVariables;

public class Agent {

    // Private properties of details of agent
    private String id;
    private String name;
    private String sessionId;
    private LoginKey key;
    private int sentCount;
    private int receiveCount;

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
    public int getReceiveCount() {
        return receiveCount;
    }
    public void setReceiveCount(int receiveCount) {
        this.receiveCount = receiveCount;
    }
    public int getSentCount() {
        return sentCount;
    }
    public void setSentCount(int sentCount) {
        this.sentCount = sentCount;
    }
    public LoginKey getKey() {
        return key;
    }
    public void setKey(LoginKey key) {
        this.key = key;
    }

    // Default constructor
    public Agent(){
        receiveCount = 0;
        sentCount = 0;
        this.sessionId = "";
        this.key = null;
    }

    // Constructor
    public Agent(String id, String name){
        this.id = id;
        this.name = name;
        this.sessionId = "";
        this.key = null;
        this.receiveCount = 0;
        this.sentCount = 0;
    }

    // Initiate contact with a supervisor to get a login key and subsequently logs into the system
    public boolean contactSupervisor(Supervisor supervisor){

        // Contact the supervisor
        this.key = supervisor.getLoginKey(this);

        // Return response
        if(this.key != null){
            return true;
        }
        else{
            return false;
        }
    }

    // Sends a SendMessage to the destination agent
    public boolean sendMessage(Agent destinationAgent, Message message)
    {
        // Check if agent already has a mailbox associated with him
        int mailboxNumber = getMailbox(destinationAgent.getId());

        // Check how many messages the sender has sent and received
        if((sentCount < 25) && (receiveCount < 25)){

            // Check how many messages the receiver has sent and received
            if((destinationAgent.getSentCount() < 25) && (destinationAgent.getReceiveCount() < 25)){
                StaticVariables.mailboxes.get(mailboxNumber).getMessages().add(message);
                sentCount++;
                destinationAgent.setReceiveCount(destinationAgent.getReceiveCount() + 1);
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

    // Get target agent's mailbox
    private int getMailbox(String agentID){

        // Loop through all mailboxes
        for(int i = 0; i < StaticVariables.mailboxes.size(); i++){

            // Compare agentID with that in the mailbox
            if(agentID.equals(StaticVariables.mailboxes.get(i).getAgentID())){
                return i;
            }
        }

        // Create new mailbox and return the index of that mailbox
        Mailbox newMailBox = new Mailbox(agentID);
        StaticVariables.mailboxes.add(newMailBox);
        return StaticVariables.mailboxes.indexOf(newMailBox);
    }
}