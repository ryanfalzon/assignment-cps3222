package UnitTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import servlets.StaticVariables;

import static org.junit.Assert.assertEquals;

public class MessageTests {

    // Properties
    Message message;

    @Before
    public void setup() {
        StaticVariables.Erase();
        message = new Message(new Agent("001", "Ryan Falzon"), new Agent("002", "Kristi Muscat"), System.currentTimeMillis(), "Hello, how are you?");
    }

    @After
    public void tearDown() {
        message = null;
    }

    @Test
    // Testing getter and setter for sourceAgent
    public void testSourceAgent(){
        Agent toChange = new Agent("007", "James Bond");
        message.setSourceAgent(toChange);
        Agent check = message.getSourceAgent();
        assertEquals(toChange, check);
    }

    @Test
    // Testing getter and setter for targetAgent
    public void testTargetAgent(){
        Agent toChange = new Agent("007", "James Bond");
        message.setTargetAgent(toChange);
        Agent check = message.getTargetAgent();
        assertEquals(toChange, check);
    }

    @Test
    // Testing getter and setter for timestamp field
    public void testTimestamp(){
        long newTimestamp = System.currentTimeMillis();
        message.setTimestamp(newTimestamp);
        long check = message.getTimestamp();
        assertEquals(newTimestamp, check);
    }

    @Test
    // Testing getter and setter for message
    public void testMessage(){
        message.setMessage("Buongiorno");
        String check = message.getMessage();
        assertEquals("Buongiorno", check);
    }
}
