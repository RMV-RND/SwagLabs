package flow;

import config.ConfigDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CreateDriver;
import utils.Utils;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static utils.Utils.dataLoadingTimeout;
import static utils.Utils.loadPage;

public class AddToCart {

    private static final Logger logger = Logger.getAnonymousLogger();

    @Given("User is logged in to Swag labs")
    public static void userIsLoggedIn() throws Exception {
        ConfigDriver.executeValidUserLogin(CreateDriver.getInstance());
        try {
            Utils.dataLoadingTimeout.until(ExpectedConditions.urlContains("/inventory.html"));
            logger.info("User is successfully Logged in");
        } catch (TimeoutException e) {
            logger.warning("The user could not log in!");
            fail();
        }
    }

    @When("It selects a product to add to cart")
    public static void addToCart() {
        Utils.loadPage();
        CreateDriver.getInstance().findElement(By.id(Utils.ADD_TO_CART)).click();
    }

    @Then("The cart icon should have displayed the numbers of products added")
    public static void isProductAddedToCart() {
        String cartNumber = CreateDriver.getInstance().findElement(By.cssSelector(Utils.ELEMENTS_ADDED_TO_CART)).getText();
        assertEquals(Integer.parseInt(cartNumber), 1);
    }

}
