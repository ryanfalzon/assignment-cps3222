package messagingapp;

public interface Supervisor {

    // Generate a login key for the given agent
    public LoginKey getLoginKey(Agent agent);
}
