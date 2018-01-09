package UnitTesting;

public class Message {

    // Private properties
    private Agent sourceAgent;
    private Agent targetAgent;
    private long timestamp;
    private String message;

    // Getters and setters
    public Agent getSourceAgent() {
        return sourceAgent;
    }
    public void setSourceAgent(Agent sourceAgentId) {
        this.sourceAgent = sourceAgentId;
    }
    public Agent getTargetAgent() {
        return targetAgent;
    }
    public void setTargetAgent(Agent targetAgentId) {
        this.targetAgent = targetAgentId;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    // Constructor
    public Message(Agent sourceAgent, Agent targetAgent, long timestamp, String message) {
        this.sourceAgent = sourceAgent;
        this.targetAgent = targetAgent;
        this.timestamp = timestamp;
        this.message = message;
    }
}