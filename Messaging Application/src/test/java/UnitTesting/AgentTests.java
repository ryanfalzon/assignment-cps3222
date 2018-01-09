package UnitTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import servlets.StaticVariables;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AgentTests {

    // Properties
    private final String VALID_KEY = "0000000000";

    @Mock
    private Supervisor supervisor;
    @InjectMocks
    private Agent agent;

    @Before
    public void setup() {
        StaticVariables.Erase();
        agent = new Agent("001", "Ryan");

        // Initiate mockito
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        agent = null;
    }

    @Test
    // Testing getter and setter of ID field
    public void testID(){
        agent.setId("007");
        String check = agent.getId();
        assertEquals("007", check);
    }

    @Test
    // Testing getter and setter of name field
    public void testName(){
        agent.setName("James Bond");
        String check = agent.getName();
        assertEquals("James Bond", check);
    }

    @Test
    // Testing getter and setter of session ID
    public void testSessionID(){
        agent.setSessionId("This is a session ID");
        String check = agent.getSessionId();
        assertEquals("This is a session ID", check);
    }

    @Test
    // Testing getter and setter of login key
    public void testLoginKey(){
        LoginKey loginKeypass = new LoginKey("0123456789", 100);
        agent.setKey(loginKeypass);
        LoginKey check = agent.getKey();
        assertEquals(loginKeypass, check);
    }

    @Test
    // Testing getter and setter for sent counter
    public void testSentCount(){
        agent.setSentCount(10);
        int check = agent.getSentCount();
        assertEquals(10, check);
    }

    @Test
    // Testing getter and setter for received counter
    public void testReceiveCount(){
        agent.setReceiveCount(20);
        int check = agent.getReceiveCount();
        assertEquals(20, check);
    }

    @Test
    // Testing contactSupervisor method in agent with valid ID
    public void testContactSupervisorValidID(){
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));

        // Exercise
        boolean check = agent.contactSupervisor(supervisor);
        assertEquals(true, check);
    }

    @Test
    // Testing contactSupervisor method in agent with an invalid ID
    public void testContactSupervisorInvalidID(){
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(null);

        // Exercise
        boolean check = agent.contactSupervisor(supervisor);
        assertEquals(false, check);
    }

    @Test
    // Testing sendMessage method in agent with valid counts
    public void testSendMessageValid(){
        // Exercise
        Agent targetAgent = new Agent("007", "James Bond");
        Message toSend = new Message(agent, targetAgent, System.currentTimeMillis(), "Hello how are you?");
        boolean check = agent.sendMessage(targetAgent, toSend);
        assertEquals(true, check);
    }

    @Test
    // Testing sendMessage method in agent with source invalid count
    public void testSendMessageInvalidSource(){
        // Exercise
        Agent targetAgent = new Agent("006", "James Bond");
        Message toSend = new Message(agent, targetAgent, System.currentTimeMillis(), "Hello how are you?");
        agent.setSentCount(25);
        agent.setReceiveCount(25);
        boolean check = agent.sendMessage(targetAgent, toSend);
        assertEquals(false, check);
    }

    @Test
    // Testing sendMessage method in agent with target invalid count
    public void testSendMessageInvalidTarget(){
        // Exercise
        Agent targetAgent = new Agent("005", "James Bond");
        Message toSend = new Message(agent, targetAgent, System.currentTimeMillis(), "Hello how are you?");
        targetAgent.setSentCount(25);
        targetAgent.setReceiveCount(25);
        boolean check = agent.sendMessage(targetAgent, toSend);
        assertEquals(false, check);
    }

    @Test
    // Testing getMailbox method in agent with a mailbox already existing
    public void testGetMailboxExisting(){
        // Create new mailbox and return the index of that mailbox
        Mailbox newMailBox = new Mailbox("001");
        StaticVariables.mailboxes.add(newMailBox);

        // Exercise
        int check = agent.getMailbox(agent.getId());
        assertEquals(0, check);
    }
}
