package messagingapp;

public class LoginKey {

    // Private properties
    private String key;
    private long timestamp;
    private String agentId;

    // Getters and setters
    public String getKey(){
        return this.key;
    }
    public void setKey(String key){
        this.key = key;
    }
    public long getTimestamp() {
        return this.timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public String getAgentId() {
        return this.agentId;
    }
    public void setAgentId(String adentId) {
        this.agentId = adentId;
    }

    // Default constructor
    public LoginKey(){

    }

    // Constructor
    public LoginKey(String key, long timestamp, String agentId){
        this.key = key;
        this.timestamp = timestamp;
        this.agentId = agentId;
    }
}
