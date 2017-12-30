package backend;

public class LoginKey {

    // Private properties
    private String key;
    private long timestamp;

    // Getters and setters
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public long getTimestamp() {
        return this.timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // Constructor
    public LoginKey(String key, long timestamp) {
        this.key = key;
        this.timestamp = timestamp;
    }
}
