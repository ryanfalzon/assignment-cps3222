package UnitTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import servlets.StaticVariables;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MailboxTests {

    // Properties
    Mailbox mailbox;

    @Before
    public void setup() {
        StaticVariables.Erase();
        mailbox = new Mailbox("001");
    }

    @After
    public void tearDown() {
        mailbox = null;
    }

    @Test
    // Testing getter and setter for agentID
    public void testAgentID(){
        mailbox.setAgentID("002");
        String check = mailbox.getAgentID();
        assertEquals("002", check);
    }

    @Test
    // Testing getter and setter for messageList
    public void testMessageList(){
        List<Message> toChange = new ArrayList<Message>();
        toChange.add(new Message(new Agent("001", "Ryan"), new Agent("002", "Kristi"), System.currentTimeMillis(), "Hello"));
        mailbox.setMessages(toChange);
        List<Message> check = mailbox.getMessages();
        assertEquals(toChange, check);
    }

    @Test
    // Testing getter and setter for counter
    public void testCounter(){
        mailbox.setCounter(10);
        int check = mailbox.getCounter();
        assertEquals(10, check);
    }


    @Test
    // Testing consumeNextMessage method in mailbox with empty mailbox
    public void testConsumeNextMessageEmpty(){
        String check = mailbox.consumeNextMessage();
        assertEquals("Mailbox Empty", check);
    }

    @Test
    // Testing consumeNextMessage method in mailbox with valid message
    public void testConsumeNextMessageValid(){
        mailbox.getMessages().add(new Message(new Agent("001", "Ryan"), new Agent("002", "Kristi"), System.currentTimeMillis(), "Hello"));
        String check = mailbox.consumeNextMessage();
        assertEquals("Hello", check);
    }

    @Test
    // Testing consumeNextMessage method in mailbox with expired message
    public void testConsumeNextMessageExpired(){
        mailbox.getMessages().add(new Message(new Agent("001", "Ryan"), new Agent("002", "Kristi"), 100, "Hello expired"));
        mailbox.getMessages().add(new Message(new Agent("001", "Ryan"), new Agent("002", "Kristi"), System.currentTimeMillis(), "Hello valid"));
        String check = mailbox.consumeNextMessage();
        assertEquals("Hello valid", check);
    }

    @Test
    // Testing hasMessages method in mailbox with valid message
    public void testHasMessagesValid(){
        mailbox.getMessages().add(new Message(new Agent("001", "Ryan"), new Agent("002", "Kristi"), System.currentTimeMillis(), "Hello"));
        boolean check = mailbox.hasMessages();
        assertEquals(true, check);
    }

    @Test
    // Testing hasMessages method in mailbox with expired message
    public void testHasMessagesExpired(){
        mailbox.getMessages().add(new Message(new Agent("001", "Ryan"), new Agent("002", "Kristi"), 100, "Hello expired"));
        boolean check = mailbox.hasMessages();
        assertEquals(false, check);
    }
}
