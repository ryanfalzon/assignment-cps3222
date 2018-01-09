package UnitTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import servlets.StaticVariables;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class MessagingSystemTests {

    // Private properties
    private MessagingSystem system;
    private final String VALID_KEY = "0000000000";

    @Mock
    private Supervisor supervisor;
    @InjectMocks
    private Agent agent;

    @Before
    public void Setup() {
        StaticVariables.Erase();
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
    // Test successful login with valid login key
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
        system.login(agent, VALID_KEY);
        system.logout(agent);

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

    @Test
    // Test an invalid SendMessage longer than 140 characters
    public void testInvalidMessage3() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));
        agent.contactSupervisor(supervisor);
        system.login(agent, VALID_KEY);

        // Exercise
        String SendMessage = system.sendMessage(agent, new Agent("002", "Kristi"), "abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij");

        // Verify
        assertEquals("Message Not Sent - Message Exceeds 140 Characters", SendMessage);
    }

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

    @Test
    // Test an invalid SendMessage whose receiver exceeds 25 messages
    public void testInvalidMessage5() {

        // Specify the return of the method without even having an implementation and login the agent
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));
        agent.contactSupervisor(supervisor);
        system.login(agent, VALID_KEY);

        // Exercise
        Agent targetAgent = new Agent("002", "Kristi");
        system.sendMessage(agent, targetAgent, "Hello, How Are You?");
        targetAgent.setReceiveCount(26);
        String message = system.sendMessage(agent, targetAgent, "Hello, How Are You?");

        // Verify
        assertEquals("Message Not Sent - Target Agent Exceeded Message Limit", message);
    }
}
