package backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class UnitTesting {

    // Private properties
    private MessagingSystem system;
    private final String VALID_KEY = "0000000000";
    private final String INVALID_KEY = "12345678";

    @Mock
    private Supervisor supervisor;
    @InjectMocks
    private Agent agent;

    @Before
    public void Setup() {
        system = new MessagingSystem();
        agent = new Agent("001", "Ryan");

        // Initiate mockito
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        system = null;
    }

    @Test
    // Test successful login with valid login key and valid timestamp
    public void testLoginSuccessful() {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));

        // Exercise
        String message = system.login(agent);

        // Verify
        assertEquals("Login Successful", message);
    }

    @Test
    // Test invalid login key with different agent
    public void testLoginUnsuccessful1() {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(INVALID_KEY, System.currentTimeMillis(), "000"));

        // Exercise
        String message = system.login(agent);

        // Verify
        assertEquals("Login Unsuccessful", message);
    }

    @Test
    // Test unsuccessful login with invalid login key
    public void testLoginUnsuccessful2() {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(INVALID_KEY, System.currentTimeMillis(), agent.getId()));

        // Exercise
        String message = system.login(agent);

        // Verify
        assertEquals("Login Unsuccessful", message);
    }

    @Test
    // Test unsuccessful login with valid login key but expired timestamp
    public void testLoginUnsuccessful3() {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis() - 70000, agent.getId()));

        // Exercise
        String message = system.login(agent);

        // Verify
        assertEquals("Login Unsuccessful", message);
    }

    @Test
    // Test a valid message
    public void testValidMessage() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);

        // Exercise
        String message = system.sendMessage(agent, new Agent("002", "Kristi"), "Hello, How Are You?");

        // Verify
        assertEquals("Message Sent", message);
    }

    @Test
    // Test an invalid message while not logged in
    public void testInvalidMessage1() {

        // Exercise
        String message = system.sendMessage(agent, new Agent("002", "Kristi"), "Hello, how are you?");

        // Verify
        assertEquals("Message Not Sent", message);
    }

    @Test
    // Test an invalid message that contains blocked words
    public void testInvalidMessage2() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);

        // Exercise
        String message = system.sendMessage(agent, new Agent("002", "Kristi"), "Does it contain ginger?");

        // Verify
        assertEquals("Message Not Sent", message);
    }

    @Test
    // Test an invalid message longer than 140 characters
    public void testInvalidMessage3() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);

        // Exercise
        String message = system.sendMessage(agent, new Agent("002", "Kristi"), "abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij");

        // Verify
        assertEquals("Message Not Sent", message);
    }

    @Test
    // Test an invalid message whose source exceeds 25 messages
    public void testInvalidMessage4() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        agent.setSentCount(26);

        // Exercise
        String message = system.sendMessage(agent, new Agent("002", "Kristi"), "Hello, how are you?");

        // Verify
        assertEquals("Message Not Sent", message);
    }

    @Test
    // Test an invalid message whose receiver exceeds 25 messages
    public void testInvalidMessage5() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");
        targetAgent.setReceivecCount(26);

        // Exercise
        String message = system.sendMessage(agent, targetAgent, "Hello, how are you?");

        // Verify
        assertEquals("Message Not Sent", message);
    }

    @Test
    // Test whether mailbox contains messages true
    public void testMessagesMailbox() {

        // Specify the return of the method without even having an implementation and login the agent and send message
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");
        system.sendMessage(agent, targetAgent, "Hello, How Are You?");

        // Exercise
        boolean message = targetAgent.getMailbox().hasMessages();

        // Verify
        assertEquals(true, message);
    }

    @Test
    // Test whether mailbox contains messages false
    public void testNoMessagesMailbox() {

        // Specify the return of the method without even having an implementation and login the agent and send message
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");

        // Exercise
        boolean message = targetAgent.getMailbox().hasMessages();

        // Verify
        assertEquals(false, message);
    }

    @Test
    // Test getting the next message in the mailbox
    public void testNextMessage() {

        // Specify the return of the method without even having an implementation and login the agent and send message
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");
        system.sendMessage(agent, targetAgent, "Hello, How Are You?");

        // Exercise
        String message = targetAgent.getMailbox().consumeNextMessage();

        // Verify
        assertEquals("Hello, How Are You?", message);
    }

    @Test
    // Test getting the next message in the mailbox when empty
    public void testNextMessageEmpty() {

        // Specify the return of the method without even having an implementation and login the agent and send message
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");

        // Exercise
        String message = targetAgent.getMailbox().consumeNextMessage();

        // Verify
        assertEquals("Mailbox Empty", message);
    }
}
