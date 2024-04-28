package LoggerManager;

import org.junit.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerManagerTest {
    @Test
    public void testLogging() {
        LoggerManager.logger.info("Testing Logs");
        File file = new File("./logINFO.txt");
        assertTrue(file.exists());
    }
}
