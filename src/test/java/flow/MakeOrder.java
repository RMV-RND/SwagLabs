package flow;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.jshell.execution.Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import utils.Utils;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MakeOrder {
    @When("User presses the cart icon")
    public static void pressCartIcon() {
        $(Utils.CART).click();
    }

    @And("Presses the Checkout button")
    public static void checkOut() {
        $(byId(Utils.CHECKOUT)).click();
    }

    @And("The user fills out the form")
    public static void fillDeliveryDetails() {
        $(byId(Utils.FIRST_NAME)).setValue(RandomStringUtils.randomAlphabetic(5));
        $(byId(Utils.LAST_NAME)).setValue(RandomStringUtils.randomAlphabetic(5));
        $(byId(Utils.ZIP_CODE)).setValue(RandomStringUtils.randomNumeric(5));
        $(byId(Utils.CONTINUE_BUTTON)).click();
        $(byId(Utils.FINISH_BUTTON)).click();
    }

    @Then("The order is placed successfully")
    public static void isOrderSuccessful() {
        String confirmOrderText = $("h2[data-test='complete-header']").getText();
        assertEquals(confirmOrderText, "Thank you for your order!");
    }
}
