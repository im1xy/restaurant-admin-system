package Mailer;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailerTest {

    private Mailer mailerUnderTest;

    @Before
    public void setUp() throws Exception {
        mailerUnderTest = new Mailer();
    }

    @Test
    public void testSendMessage() {
        final File attachment = new File("pom.xml");
        int result = mailerUnderTest.sendMessage("TESTING MAILER", "Test message", attachment);
        assertEquals(1, result);
    }
}
