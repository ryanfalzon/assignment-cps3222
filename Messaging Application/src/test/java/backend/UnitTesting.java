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
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));

        // Exercise
        agent.contactSupervisor(supervisor);
        String message = system.login(agent, VALID_KEY);

        // Verify
        assertEquals("Login Successful", message);
    }

    @Test
    // Test unsuccessful login due to being invalid
    public void testLoginUnsuccessful1() {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));

        // Exercise
        agent.contactSupervisor(supervisor);
        String message = system.login(agent, "01234");

        // Verify
        assertEquals("Login Unsuccessful - Invalid Login Key", message);
    }

    @Test
    // Test unsuccessful login due to different login key with the agent then the one entered
    public void testLoginUnsuccessful2() {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));

        // Exercise
        agent.contactSupervisor(supervisor);
        String message = system.login(agent, "0123456789");

        // Verify
        assertEquals("Login Unsuccessful - Agent ID and Login Key Do Not Match", message);
    }

    @Test
    // Test unsuccessful login with valid login key but expired timestamp
    public void testLoginUnsuccessful3() {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));

        // Exercise
        agent.contactSupervisor(supervisor);
        agent.getKey().setTimestamp(System.currentTimeMillis() - 70000);
        String message = system.login(agent, VALID_KEY);

        // Verify
        assertEquals("Login Unsuccessful - Expired Login Key", message);
    }

    @Test
    // Test a valid SendMessage
    public void testValidMessage() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));
        agent.contactSupervisor(supervisor);
        system.login(agent, VALID_KEY);

        // Exercise
        String message = system.sendMessage(agent, new Agent("002", "Kristi"), "Hello, How Are You?");

        // Verify
        assertEquals("Message Sent", message);
    }

    @Test
    // Test an invalid SendMessage while not logged in
    public void testInvalidMessage1() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));
        agent.contactSupervisor(supervisor);

        // Exercise
        String message = system.sendMessage(agent, new Agent("002", "Kristi"), "Hello, How Are You?");

        // Verify
        assertEquals("Message Not Sent - Source Agent Does Not Match Logged-in User", message);
    }

    @Test
    // Test an invalid SendMessage that contains blocked words
    public void testInvalidMessage2() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));
        agent.contactSupervisor(supervisor);
        system.login(agent, VALID_KEY);

        // Exercise
        String message = system.sendMessage(agent, new Agent("002", "Kristi"), "Hello, send recipe now.");

        // Verify
        assertEquals("Message Sent - Removed Blocked Words From Message", message);
    }
/*
    @Test
    // Test an invalid SendMessage longer than 140 characters
    public void testInvalidMessage3() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);

        // Exercise
        String SendMessage = system.sendMessage(agent, new Agent("002", "Kristi"), "abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij");

        // Verify
        assertEquals("Message Not Sent", SendMessage);
    }*/

    @Test
    // Test an invalid SendMessage whose source exceeds 25 messages
    public void testInvalidMessage4() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));
        agent.contactSupervisor(supervisor);
        system.login(agent, VALID_KEY);

        // Exercise
        system.sendMessage(agent, new Agent("002", "Kristi"), "Hello, How Are You?");
        agent.setSentCount(26);
        String message = system.sendMessage(agent, new Agent("002", "Kristi"), "Hello, How Are You?");

        // Verify
        assertEquals("Message Not Sent - Message Limit Exceeded. You Will Be Logged-Out In 10 Seconds", message);
    }
/*
    @Test
    // Test an invalid SendMessage whose receiver exceeds 25 messages
    public void testInvalidMessage5() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");
        targetAgent.setReceiveCount(26);

        // Exercise
        String SendMessage = system.sendMessage(agent, targetAgent, "Hello, how are you?");

        // Verify
        assertEquals("Message Not Sent", SendMessage);
    }

    @Test
    // Test whether mailbox contains messages true
    public void testMessagesMailbox() {

        // Specify the return of the method without even having an implementation and login the agent and send SendMessage
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");
        system.sendMessage(agent, targetAgent, "Hello, How Are You?");

        // Exercise
        boolean SendMessage = targetAgent.getMailbox().hasMessages();

        // Verify
        assertEquals(true, SendMessage);
    }

    @Test
    // Test whether mailbox contains messages false
    public void testNoMessagesMailbox() {

        // Specify the return of the method without even having an implementation and login the agent and send SendMessage
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");

        // Exercise
        boolean SendMessage = targetAgent.getMailbox().hasMessages();

        // Verify
        assertEquals(false, SendMessage);
    }

    @Test
    // Test getting the next SendMessage in the mailbox
    public void testNextMessage() {

        // Specify the return of the method without even having an implementation and login the agent and send SendMessage
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");
        system.sendMessage(agent, targetAgent, "Hello, How Are You?");

        // Exercise
        String SendMessage = targetAgent.getMailbox().consumeNextMessage();

        // Verify
        assertEquals("Hello, How Are You?", SendMessage);
    }

    @Test
    // Test getting the next SendMessage in the mailbox when empty
    public void testNextMessageEmpty() {

        // Specify the return of the method without even having an implementation and login the agent and send SendMessage
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));
        system.login(agent);
        Agent targetAgent = new Agent("002", "Kristi");

        // Exercise
        String SendMessage = targetAgent.getMailbox().consumeNextMessage();

        // Verify
        assertEquals("MailboxRequests Empty", SendMessage);
    }
*/}
