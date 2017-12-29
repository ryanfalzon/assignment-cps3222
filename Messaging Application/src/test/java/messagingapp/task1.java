package messagingapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class task1 {

    // Private properties
    private MessagingSystem system;
    private final String VALID_KEY = "0000000000";
    private final String INVALID_KEY = "12345678";

    @Mock
    private Supervisor supervisor;
    @InjectMocks
    private Agent agent;

    @Before
    public void Setup(){
        system = new MessagingSystem();
        agent = new Agent("001", "Ryan");

        // Initiate mockito
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown()
    {
        system = null;
    }

    @Test
    // Test successful login with valid login key and valid timestamp
    public void testLoginSuccessful()
    {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis(), agent.getId()));

        // Exercise
        String message = system.login(agent);

        // Verify
        assertEquals( "Login Successful", message);
    }


    @Test
    // Test unsuccessful login with invalid login key
    public void testLoginUnsuccessful1()
    {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(INVALID_KEY, System.currentTimeMillis(), agent.getId()));

        // Exercise
        String message = system.login(agent);

        // Verify
        assertEquals( "Login Unsuccessful", message);
    }

    @Test
    // Test unsuccessful login with valid login key but expired timestamp
    public void testLoginUnsuccessful2()
    {
        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis() - 70000, agent.getId()));

        // Exercise
        String message = system.login(agent);

        // Verify
        assertEquals( "Login Unsuccessful", message);
    }
}
