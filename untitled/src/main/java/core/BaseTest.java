package core;

import com.codeborne.selenide.Selenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SelenideConfig;
import org.testng.annotations.*;
import utils.Constants;

import static org.testng.Reporter.log;

public class BaseTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private LogType logType;
    private final java.util.logging.Logger log = java.util.logging.Logger.getLogger(this.getClass().getName());

    enum LogType {
        INFO, WARN, ERROR
    }

    private String formatLogMessage(String message) {
        return message.contains("%")
                ? message.replaceAll("%", "%%")
                : message;
    }

    public void logInfo(String message, Object... args) {
        logType = LogType.INFO;
        log(String.format(formatLogMessage(message), args));
    }



    @BeforeMethod(alwaysRun = true, description = "Opening web browser...")
    public void setUp() {
        logInfo("Creating web driver configuration...");
        SelenideConfig.createBrowserConfig(System.getProperty("selenide.browser", "chrome"));

        logInfo("Open browser...");
        Selenide.open(Constants.URL);
    }

    @AfterMethod(alwaysRun = true, description = "Closing web browser...")
    public void tearDown() {
        Selenide.closeWebDriver();
        logInfo("Web driver closed!");
    }
}
