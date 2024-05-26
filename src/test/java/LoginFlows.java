import config.ConfigDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import utils.CreateDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginFlows {
    @Given("Login Page")
    public static void presentInLoginPage() {
        assertTrue(CreateDriver.getInstance().getCurrentUrl().contains("https://www.saucedemo.com/"));
    }

    @When("^User tries to log in with a locked account")
    public static void logInWithLockedAccount() throws Exception {
        ConfigDriver.executeLockedOutLogin(CreateDriver.getInstance());
    }

    @Then("An error should occur")
    public static void validateError() {
        String error = CreateDriver.getInstance().findElement(By.cssSelector("div[class='error-message-container error']")).getText();
        assertEquals("Epic sadface: Sorry, this user has been locked out.", error);
    }

    @When("User types the standard user and password")
    public void userTypesTheStandardUserAndPassword() throws Exception {
        ConfigDriver.executeValidUserLogin(CreateDriver.getInstance());
    }

    @Then("Login should be successful")
    public void loginShouldBeSuccessful() {
        String correctLink = CreateDriver.getInstance().getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", correctLink);
    }
}
