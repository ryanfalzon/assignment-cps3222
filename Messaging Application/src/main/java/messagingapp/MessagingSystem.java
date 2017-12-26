package messagingapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessagingSystem {

    // Private properties
    private List<LoginKey> keys;
    private String sessionKey;
    public List<String> blockedWords;

    // Default constructor
    public MessagingSystem(){
        this.keys = new ArrayList<LoginKey>();
        this.blockedWords = Arrays.asList("recipe", "ginger", "nuclear");
    }

    // Takes a login key and agentId such that when an agent with that id tries to login she will only be allowed access if the key also matches
    public boolean registerLoginKey(LoginKey loginKey, Agent agent){

        // This method also checks that the login key is exactly 10 characters long and that the key is unique
        if((loginKey.getKey().length() == 10) && (!keys.contains(loginKey))){
            loginKey.setAgentId(agent.getId());
            keys.add(loginKey);
            return true;
        }
        else{
            return false;
        }
    }

    // Logs in a user given an agent id and key
    public String login(Agent agent, LoginKey loginKey){

        // Check that the key is not older than 1 minute
        if(((System.currentTimeMillis() - loginKey.getTimestamp()) <= 60000) && registerLoginKey(loginKey, agent) &&
                (loginKey.getAgentId().equals(agent.getId()))){
            return "Login Successful";
        }
        else{
            return null;
        }
    }

    // Sends a message from the sourceAgent to the targetAgent. Creates a message object and stores it in the targetAgent's mailbox
    public String sendMessage(Agent sourceAgent, Agent targetAgent, String message){

        // Check that targetAgent has not received more than 25 messages
        if(targetAgent.getMailbox().getCounter() < 25){

            // Check that the sourceAgent is the same as the one currently logged in
            // Check that a message does not contain any blocked words
            // Check that a message is not longer than 140 characters
            if((sourceAgent.getSessionId().equals(this.sessionKey)) && checkMessage(message) && (message.length() <= 140)){
                Message m = new Message(sourceAgent, targetAgent, System.currentTimeMillis(), message);
                targetAgent.getMailbox().getMessages().add(m);
                return "Ok";
            }
            else{
                return null;
            }
        }
        else{
            targetAgent.setSessionId(null);
            return null;
        }
    }

    // Check if passed string contains an element from the blocked words
    private boolean checkMessage(String message){

        // Iterate for all the blocked words in the list
        for(int i = 0; i < blockedWords.size(); i++){

            // If string contains current element
            if(message.contains(blockedWords.get(i))){
                return true;
            }
        }

        // If message does not contain a blocked word
        return false;
    }
}
