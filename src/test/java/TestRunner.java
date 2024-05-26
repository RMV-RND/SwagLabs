import config.ConfigDriver;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;
import utils.CreateDriver;

import java.util.logging.Logger;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("flows/Login.feature")
@SelectClasspathResource("flows/Order.feature")
public class TestRunner {

    private static final Logger logger = Logger.getAnonymousLogger();
    private static final WebDriver driver = CreateDriver.getInstance();

    @BeforeAll
    public static void openDriver() {
        try {
            ConfigDriver.execute(driver);
        } catch (Exception e) {
            logger.warning("Driver is already opened");
        }
    }

    @AfterAll
    public static void quitWebDriver() {
        CreateDriver.getInstance().quit();
    }
}

