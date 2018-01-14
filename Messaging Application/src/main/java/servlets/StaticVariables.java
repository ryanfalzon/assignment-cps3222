package servlets;

import UnitTesting.Agent;
import UnitTesting.Mailbox;

import java.util.ArrayList;
import java.util.List;

public class StaticVariables {

    // Static variables
    public static List<Agent> agents = new ArrayList<Agent>();
    public static List<String> keys = new ArrayList<String>();
    public static List<Mailbox> mailboxes = new ArrayList<Mailbox>();
    public static String sessionId;
    public static String currentUserID;

    // Static method to erase all lists
    public static void Erase(){
        agents = new ArrayList<Agent>();
        keys = new ArrayList<String>();
        mailboxes = new ArrayList<Mailbox>();
        sessionId = null;
    }
}
