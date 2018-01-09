package backend;

import servlets.StaticVariables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MessagingSystem {

    // Private properties
    public List<String> blockedWords;

    // Private final variables for session key generation
    private final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String lower = upper.toLowerCase();
    private final String numbers = "0123456789";
    private final String alphanumerical = upper + lower + numbers;

    // Default constructor
    public MessagingSystem() {
        this.blockedWords = Arrays.asList("(?i)r(?i)e(?i)c(?i)i(?i)p(?i)e", "(?i)g(?i)i(?i)n(?i)g(?i)e(?i)r", "(?i)n(?i)u(?i)c(?i)l(?i)e(?i)a(?i)r");
    }

    // Logs in a user given an agent id and key
    public String login(Agent agent, String key) {

        // Check that the key is not older than 1 minute
        if (registerLoginKey(key)) {

            // Check if the agent's ID matches the login key
            if (agent.getKey().getKey().equals(key)) {

                // Register the login key
                if ((System.currentTimeMillis() - agent.getKey().getTimestamp()) <= 60000) {

                    // Set session key of agent and system
                    String sk = getSessionKey(50);
                    StaticVariables.sessionId = sk;
                    agent.setSessionId(sk);
                    return "Login Successful";
                } else {
                    return "Login Unsuccessful - Expired Login Key";
                }
            } else {
                return "Login Unsuccessful - Agent ID and Login Key Do Not Match";
            }
        } else {
            return "Login Unsuccessful - Invalid Login Key";
        }
    }

    // Takes a login key and agentId such that when an agent with that id tries to login she will only be allowed access if the key also matches
    public boolean registerLoginKey(String loginKey) {

        // This method also checks that the login key is exactly 10 characters long and that the key is unique
        if ((loginKey.length() == 10) && (!StaticVariables.keys.contains(loginKey))) {
            StaticVariables.keys.add(loginKey);
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

    // Sends a SendMessage from the sourceAgent to the targetAgent. Creates a SendMessage object and stores it in the targetAgent's mailbox
    public String sendMessage(Agent sourceAgent, Agent targetAgent, String message) {
        // Check that the sourceAgent is the same as the one currently logged in
        if (sourceAgent.getSessionId().equals(StaticVariables.sessionId)) {

            // Check that a SendMessage is not longer than 140 characters
            if (message.length() <= 140) {

                // Try to send message
                String messageToSend = checkMessage(message);
                if (sourceAgent.sendMessage(targetAgent, new Message(sourceAgent, targetAgent, System.currentTimeMillis(), checkMessage(message)))) {

                    // Check if message contains any blocked words
                    if(messageToSend.equals(message)){
                        return "Message Sent";
                    }
                    else{
                        return "Message Sent - Removed Blocked Words From Message";
                    }
                }
                else {

                    // Check if the system will log out the logged in agent
                    if (sourceAgent.getSessionId() == null) {
                        return "Message Not Sent - Message Limit Exceeded. You Will Be Logged-Out In 10 Seconds";
                    }
                    else {
                        return "Message Not Sent - Target Agent Exceeded Message Limit";
                    }
                }
            }
            else {
                return "Message Not Sent - Message Exceeds 140 Characters";
            }
        }
        else {
            return "Message Not Sent - Source Agent Does Not Match Logged-in User";
        }
    }

    // Check if passed string contains an element from the blocked words
    private String checkMessage(String message) {

        // Iterate for all the blocked words in the list
        for (int i = 0; i < blockedWords.size(); i++) {
            message = message.replaceAll(blockedWords.get(i), "");
        }

        // If SendMessage does not contain a blocked word
        return message;
    }

    // A method to log out the passed user
    public void logout(Agent agent){

        // End the session with the agent
        agent.setSessionId("");
        agent.setKey(null);
    }
}