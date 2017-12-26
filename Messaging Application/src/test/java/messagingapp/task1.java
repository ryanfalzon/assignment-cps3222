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

        // Specify the return of the method without even having an implementation
        when(supervisor.getLoginKey(agent)).thenReturn(new LoginKey(VALID_KEY, System.currentTimeMillis()));
    }

    @After
    public void tearDown()
    {
        system = null;
    }

    @Test
    public void testLoginSuccessful()
    {
        //Exercise
        boolean message = agent.login(supervisor, system);

        //Verify
        assertEquals( true, message);
    }

    @Test
    public void testLoginUnsuccessful()
    {
        //Exercise
        String message = system.login(new Agent("001", "Ryan"), new LoginKey("000000000", System.currentTimeMillis()));

        //Verify
        assertEquals( null, message);
    }
}
