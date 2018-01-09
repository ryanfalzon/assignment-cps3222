package UnitTesting;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import servlets.StaticVariables;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JohnDoeTests {

    // Properties
    JohnDoe supervisor;

    @Before
    public void setup() {
        StaticVariables.Erase();
        supervisor= new JohnDoe();
    }

    @After
    public void tearDown() {
        supervisor = null;
    }

    @Test
    // Testing getLoginKey with valid agentID
    public void testGetLoginKeyValid(){
        LoginKey key = supervisor.getLoginKey(new Agent("001", "Ryan Falzon"));
        assertNotNull(key);
    }

    @Test
    // testing getLoginKey with invalid agentID
    public void testGetLoginKeyInvalid(){
        LoginKey key = supervisor.getLoginKey(new Agent("spy", "Kristi Muscat"));
        assertNull(key);
    }
}
