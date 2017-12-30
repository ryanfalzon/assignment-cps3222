package servlets;

import backend.Agent;
import backend.LoginKey;
import backend.Mailbox;

import java.util.ArrayList;
import java.util.List;

public class StaticVariables {

    // Static variables
    public static List<Agent> agents = new ArrayList<Agent>();
    public static List<String> keys = new ArrayList<String>();
    public static List<Mailbox> mailboxes = new ArrayList<Mailbox>();
    public static String sessionId;
}
