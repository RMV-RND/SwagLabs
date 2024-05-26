package config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CreateDriver;
import utils.Utils;

import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigDriver {

    private static final Logger logger = Logger.getAnonymousLogger();

    public static void execute(WebDriver driver) throws Exception {
        try (FileReader reader = new FileReader("src/test/resources/config/config.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            driver.navigate().to(properties.getProperty("App_Link"));
            Utils.loadPage();
        }
    }

    public static void executeValidUserLogin(WebDriver driver) throws Exception {
        try (FileReader reader = new FileReader("src/test/resources/config/config.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            driver.navigate().to(properties.getProperty("App_Link"));
            Utils.loadPage();
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Username_Locator"))).click();
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Username_Locator"))).sendKeys(properties.getProperty("Username"));
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Password_Locator"))).click();
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Password_Locator"))).sendKeys(properties.getProperty("Password"));
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Login_button"))).click();
        }
    }

    public static void executeLockedOutLogin(WebDriver driver) throws Exception {
        try (FileReader reader = new FileReader("src/test/resources/config/config.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            driver.navigate().to(properties.getProperty("App_Link"));
            Utils.loadPage();
            driver.navigate().to(properties.getProperty("App_Link"));
            Utils.loadPage();
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Username_Locator"))).click();
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Username_Locator"))).sendKeys(properties.getProperty("lockedOutUser"));
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Password_Locator"))).click();
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Password_Locator"))).sendKeys(properties.getProperty("Password"));
            CreateDriver.getInstance().findElement(By.cssSelector(properties.getProperty("Login_button"))).click();
        }
    }
}
