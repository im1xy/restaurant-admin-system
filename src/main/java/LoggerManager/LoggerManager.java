package LoggerManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

abstract public class LoggerManager {
    private final static String loggerProperties = "./src/main/resources/properties/log.properties";
    public final static Logger logger = Logger.getLogger(LoggerManager.class.getName());

    static {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(loggerProperties));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
