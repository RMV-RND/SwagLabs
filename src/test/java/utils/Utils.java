package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class Utils {
    private static final Logger logger = Logger.getAnonymousLogger();

    public static final FluentWait<WebDriver> dataLoadingTimeout = new WebDriverWait(CreateDriver.getInstance(), Duration.ofSeconds(3))
            .ignoring(StaleElementReferenceException.class)
            .ignoring(NoSuchElementException.class)
            .ignoring(TimeoutException.class);

    public static final JavascriptExecutor jsExecutor = (JavascriptExecutor) CreateDriver.getInstance();

    public static final String ADD_TO_CART = "add-to-cart-sauce-labs-backpack";
    public static final String CART = "a[data-test = 'shopping-cart-link']";
    public static final String ELEMENTS_ADDED_TO_CART = "span[data-test='shopping-cart-badge']";
    public static final String CHECKOUT = "checkout";
    public static final String FIRST_NAME = "first-name";
    public static final String LAST_NAME = "last-name";
    public static final String ZIP_CODE = "postal-code";
    public static final String CONTINUE_BUTTON = "continue";
    public static final String FINISH_BUTTON = "finish";


    public static void loadPage() {
        if (jsExecutor.executeScript("return document.readyState").toString().equals("complete")) {
            logger.info("Page has loaded");
        }
    }
}
