package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MessagingSystem {

    // Private properties
    private List<LoginKey> keys;
    private String sessionKey;
    public List<String> blockedWords;

    // Private final variables for session key generation
    private final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String lower = upper.toLowerCase();
    private final String numbers = "0123456789";
    private final String alphanumerical = upper + lower + numbers;

    // Default constructor
    public MessagingSystem() {
        this.keys = new ArrayList<LoginKey>();
        this.blockedWords = Arrays.asList("recipe", "ginger", "nuclear");
    }

    // Logs in a user given an agent id and key
    public String login(Agent agent) {

        // Call the login method of the agent
        LoginKey keyToValidate = agent.login();

        // Check that the key is not older than 1 minute
        if ((System.currentTimeMillis() - keyToValidate.getTimestamp()) <= 60000) {

            // Check if the agent's ID matches the login key
            if (agent.getId().equals(keyToValidate.getAgentId())) {

                // Register the login key
                if (registerLoginKey(keyToValidate, agent)) {

                    // Set session key of agent and system
                    String sk = getSessionKey(50);
                    this.sessionKey = sk;
                    agent.setSessionId(sk);
                    return "Login Successful";
                } else {
                    return "Login Unsuccessful";
                }
            } else {
                return "Login Unsuccessful";
            }
        } else {
            return "Login Unsuccessful";
        }
    }

    // Takes a login key and agentId such that when an agent with that id tries to login she will only be allowed access if the key also matches
    public boolean registerLoginKey(LoginKey loginKey, Agent agent) {

        // This method also checks that the login key is exactly 10 characters long and that the key is unique
        if ((loginKey.getKey().length() == 10) && (!keys.contains(loginKey))) {
            loginKey.setAgentId(agent.getId());
            keys.add(loginKey);
            return true;
        } else {
            return false;
        }
    }

    // Method to generate a session key
    public String getSessionKey(int counter) {

        Random random = new Random();
        String sessionKey = "";

        // Generate a session key according to the length passed to the method
        for (int i = 0; i < counter; i++) {
            sessionKey += alphanumerical.charAt(random.nextInt(alphanumerical.length()));
        }

        return sessionKey;
    }

    // Sends a message from the sourceAgent to the targetAgent. Creates a message object and stores it in the targetAgent's mailbox
    public String sendMessage(Agent sourceAgent, Agent targetAgent, String message) {
        // Check that the sourceAgent is the same as the one currently logged in
        if (sourceAgent.getSessionId().equals(this.sessionKey)) {

            // Check that a message does not contain any blocked words
            if (checkMessage(message)) {

                // Check that a message is not longer than 140 characters
                if (message.length() <= 140) {

                    // Try to send message
                    if (sourceAgent.sendMessage(targetAgent, new Message(sourceAgent, targetAgent, System.currentTimeMillis(), message))) {
                        return "Message Sent";
                    } else {
                        return "Message Not Sent";
                    }
                } else {
                    return "Message Not Sent";
                }
            } else {
                return "Message Not Sent";
            }
        } else {
            return "Message Not Sent";
        }
    }

    // Check if passed string contains an element from the blocked words
    private boolean checkMessage(String message) {

        // Iterate for all the blocked words in the list
        for (int i = 0; i < blockedWords.size(); i++) {

            // If string contains current element
            if (message.contains(blockedWords.get(i))) {
                return false;
            }
        }

        // If message does not contain a blocked word
        return true;
    }
}