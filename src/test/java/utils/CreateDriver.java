package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class CreateDriver {
    private final static Logger logger = Logger.getAnonymousLogger();

    private static WebDriver driver;

    private CreateDriver() {
        Configuration.holdBrowserOpen = true;
        driver = initWebDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    private WebDriver initWebDriver() {
        try {
            return startChromeDriver();
        } catch (IOException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            return new CreateDriver().getDriver();
        }
        return driver;
    }

    private static WebDriver startChromeDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/test/resources/config/config.properties"));
        if (properties.getProperty("useHeadless").equals("true")) {
            logger.info("This is Headless Chrome Driver running.");
            return getFullScreenDriver(new ChromeDriver(getHeadlessOptions()));
        } else {
            logger.info("This is Chrome Driver running.");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            return getFullScreenDriver(new ChromeDriver(options));
        }
    }

    private static WebDriver getFullScreenDriver(RemoteWebDriver driver) {
        driver.manage().window().maximize();
        return driver;
    }


    private static ChromeOptions getHeadlessOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return options;
    }

    private WebDriver getDriver() {
        return driver;
    }
}
