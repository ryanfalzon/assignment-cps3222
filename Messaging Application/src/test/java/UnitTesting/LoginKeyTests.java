package UnitTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import servlets.StaticVariables;

import static org.junit.Assert.assertEquals;

public class LoginKeyTests {

    // Properties
    LoginKey loginKey;

    @Before
    public void setup() {
        StaticVariables.Erase();
        loginKey = new LoginKey("0123456789", System.currentTimeMillis());
    }

    @After
    public void tearDown() {
        loginKey = null;
    }

    @Test
    // Testing getter and setter for key field
    public void testKey(){
        loginKey.setKey("9876543210");
        String check = loginKey.getKey();
        assertEquals("9876543210", check);
    }

    @Test
    // Testing getter and setter for timestamp field
    public void testTimestamp(){
        long newTimestamp = System.currentTimeMillis();
        loginKey.setTimestamp(newTimestamp);
        long check = loginKey.getTimestamp();
        assertEquals(newTimestamp, check);
    }
}
